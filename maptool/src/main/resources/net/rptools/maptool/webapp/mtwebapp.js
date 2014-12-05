/*
 * This software Copyright by the RPTools.net development team, and
 * licensed under the GPL Version 3 or, at your option, any later version.
 *
 * MapTool 2 Source Code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this source Code.  If not, see <http://www.gnu.org/licenses/>
 */

var MapToolAPI = {};

var MapTool = {};


$(document).ready(function() {

    // Values used when we send initiative commands back.
    var currentInitiative;
    var currentRound;

    ////////////////////////////////////////////////////////////////////////////
    //
    // Create the web socket for the information exchange.
    //
    ////////////////////////////////////////////////////////////////////////////
    MapTool.ws = new WebSocket('ws:' + document.location.host + '/ws/');

    MapTool.ws.onopen = function() {
        console.log("Opened");
    };

    MapTool.ws.onmessage = function(event) {
        if (event.data === 'keep-alive') {
            console.log('Received keep alive');
        } else {
            console.log(event.data);
            var msg = jQuery.parseJSON(event.data);
            if (msg.messageType === 'initiative') {
                updateInitiative(msg);
            } else if (msg.messageType === 'propertyTypes') {
                updatePropertyTypes(msg);
            }
        }
    };

    MapTool.ws.onclose = function() {
        console.log("Closed");
    };

    MapTool.ws.onerror = function(err) {
        console.log("Error: " + err);
    };


    // Make sure that we explicitly close the web socket before before browser disposed of page incase the
    // browser doesn't actually do it.
    window.onbeforeunload = function() {
        MapTool.ws.onclose = function () {}; // disable onclose handler first
        MapTool.ws.close()
    };

    ////////////////////////////////////////////////////////////////////////////
    //
    // Function used to update the initiative values on the web page.
    //
    ////////////////////////////////////////////////////////////////////////////
    function updateInitiative(data) {
        console.log('Received Initiative');
        var initList =  $('#initList');
        initList.empty();

        for (var prop in data) {
            console.log(prop);
        }


        currentInitiative = data.data.current;
        currentRound = data.data.round;


        var source = $('#init-element').html();
        var template = Handlebars.compile(source);

        var entries = data.data.initiative;
        var toggle = 0;
        for (var i = 0; i < entries.length; i++) {
            console.log('name = ' + entries[i].name + ' => ' + entries[i].initiative);
            var initDivClass;
            if (entries[i].holding === 'true') {
                initDivClass = 'initHolding';
            } else if (currentInitiative == i) {
                initDivClass = 'initCurrent';
            } else if (i < data.data.current) {
                initDivClass = 'initDone';
            } else {
                initDivClass = 'initPending';
            }

            var ownerClass;
            if (entries[i].playerOwns === "true") {
                ownerClass = 'playerIsOwner';
            } else {
                ownerClass = 'playerIsNotOwner';
            }

            /* FIXME: Remove this its just for testing.
            if (toggle == 0) {
                toggle = 1;
                ownerClass = 'playerIsOwner';
            } else {
                toggle = 0;
                ownerClass = 'playerIsNotOwner';
            }*/


            var vals = {
                'tokenName': entries[i].name,
                'initiative': entries[i].initiative,
                'initDivClass': initDivClass,
                'tokenIndex': entries[i].tokenIndex,
                'tokenId': entries[i].id,
                'tokenOwnerClass': ownerClass
            };

            var html = template(vals);
            initList.append(html);
        }
        $('#initRound').html(currentRound);
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // Function used to update the property types for tokens.
    //
    ////////////////////////////////////////////////////////////////////////////
    function updatePropertyTypes(data) {
        data.propertyTypes.forEach(function(ptype) {
            var templateName = ptype.name.replace(' ', '-');
            var template;
            template = "<template id = '" + + "'>";
            template = "</template>"
        });
    }

    ////////////////////////////////////////////////////////////////////////////
    // Add call back to the initiative buttons.
    $('.initButton').on('click', function() {
        console.log($(this));
        MapTool.ws.send(JSON.stringify(
            {
                messageType: 'initiative',
                data: {
                    command: $(this).data('initcommand'),
                    currentInitiative: currentInitiative,
                    currentRound: currentRound
                }
            }
        ));
        console.log('Next Initiative clicked');
    });

    $('#initList').delegate('.tokenInitButton', 'click', function() {
        console.log($(this));
        console.log($(this).data('tokenid'));
        MapTool.ws.send(JSON.stringify(
            {
                messageType: 'initiative',
                data: {
                    command: $(this).data('initcommand'),
                    token: $(this).data('tokenid'),
                    currentInitiative: currentInitiative,
                    currentRound: currentRound,
                    tokenIndex: $(this).data('tokenindex')
                }
            }
        ));
        console.log('Toggle hold clicked');
    });



});
