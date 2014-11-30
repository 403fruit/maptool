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


$(document).ready(function(){


    function updateInitiative(data) {
        console.log('Received Initiative');
        var initList =  $('#initList');
        initList.empty();

        for (var prop in data) {
            console.log(prop);
        }


        var source = $('#init-element').html();
        var template = Handlebars.compile(source);

        var entries = data.data.initiative;
        for (var i = 0; i < entries.length; i++) {
        //data.data.initiative.forEach(function(entry) {
            console.log('name = ' + entries[i].name + ' => ' + entries[i].initiative);
            var initDivClass;
            if (data.data.holding) {
                initDivClass = 'initHolding';
            } else if (data.data.current == i) {
                initDivClass = 'initCurrent';
            } else if (i < data.data.current) {
                initDivClass = 'initDone';
            } else {
                initDivClass = 'initPending';
            }

            var vals = {
                'tokenName': entries[i].name,
                'initiative': entries[i].initiative,
                'initDivClass': initDivClass
            };

            var html = template(vals);
            initList.append(html);
        }
    }




    var ws = new WebSocket('ws:' + document.location.host + '/ws/');

    ws.onopen = function() {
        console.log("Opened");
    };

    ws.onmessage = function(event) {
        if (event.data === 'keep-alive') {
            console.log('Received keep alive');
        } else {
            console.log(event.data);
            var msg = jQuery.parseJSON(event.data);
            if (msg.messageType === 'initiative') {
                updateInitiative(msg);
            }
        }
    };

    ws.onclose = function() {
        console.log("Closed");
    };

    ws.onerror = function(err) {
        console.log("Error: " + err);
    };


    //
    // Make sure that we explicitly close the web socket before before browser disposed of page incase the
    // browser doesn't actually do it.
    window.onbeforeunload = function() {
        ws.onclose = function () {}; // disable onclose handler first
        ws.close()
    };

});
