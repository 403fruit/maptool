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

package net.rptools.maptool.webapi;

import net.rptools.maptool.client.MapTool;
import net.rptools.maptool.client.ui.zone.ZoneRenderer;
import net.rptools.maptool.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class WebTokenInfo {

    private static final WebTokenInfo instance = new WebTokenInfo();


    private WebTokenInfo() {}



    public static WebTokenInfo getInstance() {
        return instance;
    }



    public Token findTokenFromId(String tokenId) {
        final GUID id = new GUID(tokenId);

        final List<Token> tokenList = new ArrayList<>();

        List<ZoneRenderer> zrenderers = MapTool.getFrame().getZoneRenderers();
        for (ZoneRenderer zr : zrenderers) {
            tokenList.addAll(zr.getZone().getTokensFiltered(new Zone.Filter() {
                public boolean matchToken(Token t) {
                    return t.getId().equals(id);
                }
            }));

            if (tokenList.size() > 0) {
                System.out.println("DEBUG: Here (> 0)");
                break;
            }
        }

        if (tokenList.size() > 0) {
            return tokenList.get(0);
        } else {
            return null;
        }
    }


    void sendTokenInfo(MTWebSocket mtws, String inResponseTo, JSONObject data) {

        String tokenId = data.getString("tokenId");
        Token token = findTokenFromId(tokenId);

        if (token == null) {
            System.out.println("DEBUG: sendTokenInfo(): Unable to find token " + tokenId);
            return;
            // FIXME: log this error
        }

        JSONObject jobj = new JSONObject();
        jobj.put("tokenId", tokenId);
        jobj.put("name", token.getName());
        jobj.put("label", token.getLabel());
        jobj.put("notes", token.getNotes());


        JSONObject jprop = new JSONObject();

        for (TokenProperty tp : MapTool.getCampaign().getTokenPropertyList(token.getPropertyType())) {
            JSONObject jp= new JSONObject();
            jp.put("name", tp.getName());
            if (tp.getShortName() != null) {
                jp.put("shortName", tp.getShortName());
            }
            if (tp.getDefaultValue() != null) {
                jp.put("defaultValue", tp.getDefaultValue());
            }
            jp.put("value", token.getProperty(tp.getName()));
            jp.put("showOnStatSheet", tp.isShowOnStatSheet());

            jprop.put(tp.getName(), jp);
        }

        jobj.put("properties", jprop);

        JSONArray jmacros = new JSONArray();

        for (MacroButtonProperties macro : token.getMacroList(false)) {
            JSONObject jmb = new JSONObject();
            jmb.put("label", macro.getLabel());
            jmb.put("tooltip", macro.getEvaluatedToolTip());
            jmb.put("index", macro.getIndex());
            jmb.put("fontColor", macro.getFontColorAsHtml());
            jmb.put("displayGroup", macro.getGroupForDisplay());
            jmb.put("group", macro.getGroup());
            jmb.put("index", macro.getIndex());
            jmb.put("autoExecute", macro.getAutoExecute());
            jmb.put("maxWidth", macro.getMaxWidth());
            jmb.put("minWidth", macro.getMinWidth());
            jmb.put("applyToTokens", macro.getApplyToTokens());

            jmacros.add(jmb);
        }


        jobj.put("macros", jmacros);

        mtws.sendMessage("tokenInfo", inResponseTo, jobj);

    }


    void processMacro(JSONObject data) {
        // FIXME: need to check parameters
        if ("callMacro".equalsIgnoreCase(data.getString("command"))) {
            Token token = findTokenFromId(data.getString("tokenId"));

            MacroButtonProperties macro = token.getMacro(data.getInt("macroIndex"), false);
            macro.executeMacro(token.getId());
        }
    }
}
