/*
 * This software Copyright by the RPTools.net development team, and licensed under the Affero GPL Version 3 or, at your option, any later version.
 *
 * MapTool Source Code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public License * along with this source Code. If not, please visit <http://www.gnu.org/licenses/> and specifically the Affero license text
 * at <http://www.gnu.org/licenses/agpl.html>.
 */
package net.rptools.maptool.client.ui.syntax;

import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.*;

/**
 * Auto-generated source created using TokenMakerMaker https://github.com/bobbylight/TokenMakerMaker
 */

public class MapToolScriptTokenMaker extends AbstractJFlexCTokenMaker {

	/** This character denotes the end of file */
	public static final int YYEOF = -1;

	/** initial size of the lookahead buffer */
	private static final int ZZ_BUFFERSIZE = 16384;

	/** lexical states */
	public static final int YYINITIAL = 0;
	public static final int MLC = 1;

	/**
	 * Translates characters to character classes
	 */
	private static final String ZZ_CMAP_PACKED = "\11\0\1\21\1\10\1\0\1\21\1\17\22\0\1\65\1\23\1\15" +
			"\1\20\1\1\1\47\1\70\1\7\2\45\1\47\1\31\1\44\1\24" +
			"\1\27\1\50\1\4\3\16\4\6\2\3\1\54\1\44\1\22\1\46" +
			"\1\25\1\44\1\20\1\40\1\5\1\56\1\60\1\30\1\36\1\62" +
			"\1\51\1\53\1\1\1\64\1\41\1\63\1\61\1\57\1\52\1\1" +
			"\1\34\1\42\1\32\1\12\1\66\1\55\1\26\1\67\1\1\1\45" +
			"\1\11\1\45\1\17\1\2\1\0\1\40\1\14\1\56\1\60\1\30" +
			"\1\37\1\62\1\72\1\53\1\1\1\64\1\41\1\63\1\13\1\57" +
			"\1\52\1\1\1\35\1\42\1\33\1\12\1\66\1\73\1\26\1\67" +
			"\1\1\1\43\1\71\1\43\1\44\uff81\0";

	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	private static final String ZZ_ACTION_PACKED_0 = "\2\0\2\1\2\2\1\1\1\3\1\4\1\5\1\6" +
			"\1\7\3\10\1\1\3\5\1\1\1\5\1\11\1\5" +
			"\1\1\2\5\1\1\1\5\1\1\2\10\1\12\1\13" +
			"\7\12\1\0\1\14\1\0\2\14\1\1\1\3\1\15" +
			"\1\0\1\3\1\1\2\6\1\16\1\0\1\17\11\1" +
			"\1\5\2\1\1\5\3\1\1\5\1\1\1\0\2\12" +
			"\2\0\2\12\3\0\1\17\1\0\1\20\1\1\1\3" +
			"\1\21\2\3\1\15\1\3\1\1\1\6\1\22\1\6" +
			"\1\0\10\1\1\5\12\1\1\23\2\12\2\0\2\12" +
			"\4\0\1\3\1\1\1\6\1\24\1\1\1\25\13\1" +
			"\1\12\1\0\2\12\1\26\3\0\1\3\1\1\1\6" +
			"\2\1\1\5\1\0\4\1\1\5\1\12\4\0\1\3" +
			"\1\1\1\6\2\1\1\0\1\1\2\0\1\1\2\0" +
			"\2\1\12\0\1\27\1\1\26\0\1\27\15\0";

	private static int[] zzUnpackAction() {
		int[] result = new int[233];
		int offset = 0;
		offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAction(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/**
	 * Translates a state to a row index in the transition table
	 */
	private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

	private static final String ZZ_ROWMAP_PACKED_0 = "\0\0\0\74\0\170\0\264\0\360\0\u012c\0\u0168\0\u01a4" +
			"\0\170\0\u01e0\0\u021c\0\u0258\0\u0294\0\170\0\u02d0\0\u030c" +
			"\0\u0348\0\u0384\0\u03c0\0\u03fc\0\u0438\0\170\0\u0474\0\u04b0" +
			"\0\u04ec\0\u0528\0\u0564\0\u05a0\0\u05dc\0\u0618\0\u0654\0\u0690" +
			"\0\170\0\u06cc\0\u0708\0\u0744\0\u0780\0\u07bc\0\u07f8\0\u0834" +
			"\0\u0870\0\u08ac\0\u030c\0\u08e8\0\u0924\0\u0960\0\u099c\0\170" +
			"\0\u09d8\0\u0a14\0\u0a50\0\u0a8c\0\u0ac8\0\170\0\u0b04\0\u0b40" +
			"\0\u0b7c\0\u0bb8\0\u0bf4\0\u0c30\0\u0c6c\0\u0ca8\0\u0ce4\0\u0d20" +
			"\0\u0d5c\0\u0d98\0\u0dd4\0\u0e10\0\264\0\u0e4c\0\u0e88\0\u0ec4" +
			"\0\u0f00\0\u0f3c\0\u0f78\0\u0fb4\0\u0ff0\0\u102c\0\u1068\0\u10a4" +
			"\0\u10e0\0\u111c\0\u1158\0\u1194\0\u11d0\0\u120c\0\u0924\0\u1248" +
			"\0\u1284\0\170\0\u12c0\0\u12fc\0\u09d8\0\u1338\0\u1374\0\u13b0" +
			"\0\170\0\u13ec\0\u1428\0\u1464\0\u14a0\0\u14dc\0\u1518\0\u1554" +
			"\0\u1590\0\u15cc\0\u1608\0\u1644\0\u1680\0\u16bc\0\u16f8\0\u1734" +
			"\0\u1770\0\u17ac\0\u17e8\0\u1824\0\u1860\0\u189c\0\170\0\u18d8" +
			"\0\u1914\0\u1950\0\u198c\0\u19c8\0\u1a04\0\u1a40\0\u1a7c\0\u1ab8" +
			"\0\u1af4\0\u1b30\0\u1b6c\0\u1ba8\0\170\0\u1be4\0\264\0\u1c20" +
			"\0\u1c5c\0\u1c98\0\u1cd4\0\u1d10\0\u1d4c\0\u1d88\0\u1dc4\0\u1e00" +
			"\0\u1e3c\0\u1e78\0\u1eb4\0\u1ef0\0\u1f2c\0\u1f68\0\u1fa4\0\u1fe0" +
			"\0\u201c\0\u2058\0\u2094\0\u20d0\0\u210c\0\u2148\0\u2184\0\u21c0" +
			"\0\u21fc\0\u2238\0\u2274\0\u22b0\0\u22ec\0\u2328\0\u2364\0\u23a0" +
			"\0\u1fa4\0\u23dc\0\u2418\0\u2454\0\u2490\0\u24cc\0\u2508\0\u2544" +
			"\0\u2580\0\u25bc\0\u25f8\0\u2634\0\u2670\0\u26ac\0\u26e8\0\u2724" +
			"\0\u2760\0\u279c\0\u27d8\0\u2814\0\u2850\0\u288c\0\u28c8\0\u2904" +
			"\0\u2940\0\u297c\0\u29b8\0\170\0\u29f4\0\u2a30\0\u2a6c\0\u2aa8" +
			"\0\u2ae4\0\u2b20\0\u2b5c\0\u2b98\0\u2bd4\0\u2c10\0\u2c4c\0\u2c88" +
			"\0\u2cc4\0\u2d00\0\u2d3c\0\u2d78\0\u2db4\0\u2df0\0\u2e2c\0\u2e68" +
			"\0\u2ea4\0\u2ee0\0\u2f1c\0\u2f58\0\u2f94\0\u2fd0\0\u300c\0\u3048" +
			"\0\u3084\0\u30c0\0\u30fc\0\u3138\0\u3174\0\u31b0\0\u31ec\0\u3228" +
			"\0\u3264";

	private static int[] zzUnpackRowMap() {
		int[] result = new int[233];
		int offset = 0;
		offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackRowMap(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int high = packed.charAt(i++) << 16;
			result[j++] = high | packed.charAt(i++);
		}
		return j;
	}

	/**
	 * The transition table of the DFA
	 */
	private static final int[] ZZ_TRANS = zzUnpackTrans();

	private static final String ZZ_TRANS_PACKED_0 = "\1\3\2\4\1\5\1\6\1\7\1\5\1\10\1\11" +
			"\1\3\1\12\1\4\1\7\1\13\1\5\2\3\1\14" +
			"\1\15\2\16\1\17\1\4\1\20\1\21\1\16\2\22" +
			"\2\23\2\24\2\4\1\25\1\26\1\3\1\26\1\17" +
			"\2\16\1\27\1\4\1\30\1\16\1\31\1\32\1\4" +
			"\1\33\1\4\1\34\1\35\1\4\1\14\2\4\1\36" +
			"\1\37\1\27\1\31\10\40\1\41\13\40\1\42\11\40" +
			"\1\43\1\44\11\40\1\45\3\40\1\46\14\40\1\47" +
			"\1\50\75\0\6\4\2\0\1\51\3\4\1\0\1\4" +
			"\7\0\1\4\1\0\1\4\1\0\11\4\6\0\3\4" +
			"\1\0\10\4\1\0\2\4\2\0\2\4\3\52\2\5" +
			"\1\52\1\5\2\0\4\52\1\0\1\5\1\0\1\52" +
			"\5\0\1\52\1\53\1\54\1\0\11\52\6\0\3\52" +
			"\1\0\10\52\1\0\2\52\2\0\5\52\2\5\1\52" +
			"\1\5\2\0\4\52\1\0\1\5\1\0\1\52\5\0" +
			"\1\55\1\53\1\54\1\0\11\52\6\0\3\52\1\0" +
			"\10\52\1\0\2\52\2\0\2\52\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\6\4\1\56\2\4\6\0\3\4\1\0\10\4" +
			"\1\0\2\4\2\0\2\4\7\57\1\60\1\61\1\62" +
			"\62\57\1\0\6\4\2\0\1\51\1\4\1\63\1\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\11\4" +
			"\6\0\3\4\1\0\4\4\1\63\3\4\1\0\2\4" +
			"\2\0\2\4\10\13\1\64\1\65\3\13\1\66\56\13" +
			"\21\0\1\14\43\0\1\14\31\0\1\67\22\0\1\16" +
			"\73\0\1\16\30\0\2\70\1\0\1\70\7\0\1\70" +
			"\56\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\71\1\0\1\4\1\0\11\4\6\0\3\4\1\0" +
			"\10\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\2\4\2\72\5\4\6\0\3\4\1\0\2\4" +
			"\1\73\5\4\1\0\2\4\2\0\2\4\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\74\1\0\11\4\6\0\3\4\1\0\2\4\1\75" +
			"\5\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\2\4\2\76\2\4\1\77\2\4\6\0\3\4" +
			"\1\0\2\4\1\100\5\4\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\101\1\0\2\102\7\4\6\0\3\4" +
			"\1\0\1\103\7\4\1\0\2\4\2\0\1\4\1\103" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\11\4\6\0\2\4\1\104" +
			"\1\0\10\4\1\0\2\4\2\0\2\4\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\4\4\2\105\3\4\6\0\3\4\1\0" +
			"\10\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\11\4\6\0\1\106\2\4\1\0\10\4\1\0" +
			"\2\4\2\0\1\106\1\4\1\0\6\4\2\0\1\51" +
			"\3\4\1\0\1\4\7\0\1\4\1\0\1\4\1\0" +
			"\11\4\6\0\3\4\1\0\2\4\1\107\5\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\11\4" +
			"\6\0\2\4\1\110\1\0\10\4\1\0\2\4\2\0" +
			"\2\4\1\0\6\4\2\0\1\51\3\4\1\0\1\4" +
			"\7\0\1\4\1\0\1\4\1\0\2\105\7\4\6\0" +
			"\3\4\1\0\6\4\1\111\1\4\1\0\2\4\2\0" +
			"\2\4\1\0\6\4\2\0\1\51\3\4\1\0\1\4" +
			"\7\0\1\4\1\0\1\4\1\0\6\4\1\112\2\4" +
			"\6\0\3\4\1\0\10\4\1\0\2\4\2\0\2\4" +
			"\70\0\1\16\74\0\1\16\2\0\10\40\1\0\13\40" +
			"\1\0\12\40\1\0\32\40\26\0\1\113\47\0\10\40" +
			"\1\0\13\40\1\0\5\40\2\114\3\40\1\0\13\40" +
			"\1\115\16\40\34\0\2\116\17\0\1\117\20\0\10\40" +
			"\1\0\13\40\1\0\5\40\2\120\3\40\1\0\32\40" +
			"\2\0\10\40\1\0\13\40\1\0\12\40\1\0\15\40" +
			"\1\121\14\40\1\0\1\122\32\0\2\123\115\0\1\122" +
			"\15\0\1\122\12\0\1\124\61\0\7\52\2\0\4\52" +
			"\1\0\1\52\1\0\1\52\5\0\1\52\1\0\1\52" +
			"\1\0\11\52\6\0\3\52\1\0\10\52\1\0\2\52" +
			"\2\0\5\52\2\125\1\52\1\125\2\0\4\52\1\0" +
			"\1\125\1\0\1\52\3\0\1\126\1\0\1\52\1\0" +
			"\1\52\1\126\11\52\6\0\3\52\1\0\10\52\1\0" +
			"\2\52\2\0\5\52\4\127\2\0\3\52\1\127\1\0" +
			"\1\127\1\0\1\52\5\0\1\52\1\0\1\127\1\0" +
			"\4\52\3\127\2\52\6\0\3\52\1\0\1\52\1\127" +
			"\1\52\1\127\4\52\1\0\2\52\2\0\2\52\1\0" +
			"\6\4\2\0\1\51\3\4\1\0\1\4\7\0\1\4" +
			"\1\0\1\4\1\0\2\4\2\130\5\4\6\0\3\4" +
			"\1\0\10\4\1\0\2\4\2\0\2\4\7\131\1\132" +
			"\1\0\63\131\7\0\1\132\64\0\4\131\1\133\1\131" +
			"\1\134\1\135\1\0\1\57\1\136\3\57\1\133\14\131" +
			"\1\57\1\131\1\57\1\131\1\57\34\131\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\4\4\2\137\3\4\6\0\3\4\1\0" +
			"\10\4\1\0\2\4\2\0\2\4\11\64\1\140\3\64" +
			"\1\141\62\64\1\13\1\64\2\13\1\0\1\13\1\142" +
			"\4\13\14\64\1\13\1\64\1\13\1\64\1\13\34\64" +
			"\24\0\1\143\47\0\3\52\2\70\1\52\1\70\2\0" +
			"\4\52\1\0\1\70\1\0\1\52\5\0\1\52\1\0" +
			"\1\54\1\0\11\52\6\0\3\52\1\0\10\52\1\0" +
			"\2\52\2\0\2\52\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\11\4" +
			"\6\0\1\4\1\144\1\4\1\0\10\4\1\0\2\4" +
			"\2\0\2\4\1\0\6\4\2\0\1\51\1\145\2\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\11\4" +
			"\6\0\3\4\1\0\10\4\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\11\4\6\0\3\4\1\0" +
			"\2\4\1\146\4\4\1\147\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\10\4\1\150\6\0\3\4" +
			"\1\0\10\4\1\0\2\4\2\0\2\4\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\7\4\1\151\1\4\6\0\3\4\1\0" +
			"\10\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\6\4\1\152\2\4\6\0\3\4\1\0\10\4" +
			"\1\0\2\4\2\0\2\4\1\0\6\4\2\0\1\51" +
			"\3\4\1\0\1\4\7\0\1\4\1\0\1\4\1\0" +
			"\7\4\1\153\1\4\6\0\3\4\1\0\10\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\2\4" +
			"\2\154\5\4\6\0\3\4\1\0\10\4\1\0\2\4" +
			"\2\0\2\4\1\0\6\4\2\0\1\51\3\4\1\0" +
			"\1\4\7\0\1\4\1\0\1\4\1\0\7\4\1\155" +
			"\1\4\6\0\3\4\1\0\10\4\1\0\2\4\2\0" +
			"\2\4\1\0\6\4\2\0\1\51\3\4\1\0\1\4" +
			"\7\0\1\4\1\0\1\4\1\0\6\4\1\156\2\4" +
			"\6\0\3\4\1\0\10\4\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\11\4\6\0\2\4\1\157" +
			"\1\0\10\4\1\0\2\4\2\0\2\4\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\11\4\6\0\3\4\1\0\3\4\1\160" +
			"\4\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\11\4\6\0\2\4\1\161\1\0\10\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\1\162" +
			"\2\4\1\0\1\4\7\0\1\4\1\0\1\4\1\0" +
			"\11\4\6\0\3\4\1\0\3\4\1\163\4\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\6\4" +
			"\1\164\2\4\6\0\3\4\1\0\10\4\1\0\2\4" +
			"\2\0\2\4\1\0\6\4\2\0\1\51\3\4\1\0" +
			"\1\4\7\0\1\4\1\0\1\4\1\0\2\165\7\4" +
			"\6\0\3\4\1\0\10\4\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\11\4\6\0\3\4\1\0" +
			"\1\4\1\166\6\4\1\0\2\4\2\0\2\4\25\0" +
			"\1\167\46\0\10\40\1\0\13\40\1\0\12\40\1\0" +
			"\12\40\1\170\17\40\2\0\10\40\1\0\13\40\1\0" +
			"\12\40\1\0\1\40\1\171\30\40\54\0\1\172\62\0" +
			"\1\173\32\0\10\40\1\0\13\40\1\0\5\40\2\174" +
			"\3\40\1\0\32\40\2\0\10\40\1\0\13\40\1\0" +
			"\12\40\1\0\15\40\1\175\14\40\1\0\1\176\55\0" +
			"\1\176\15\0\1\176\32\0\2\177\43\0\4\200\5\0" +
			"\1\200\1\0\1\200\11\0\1\200\5\0\3\200\15\0" +
			"\1\200\1\0\1\200\13\0\3\52\2\125\1\52\1\125" +
			"\2\0\4\52\1\0\1\125\1\0\1\52\5\0\1\52" +
			"\1\0\1\52\1\0\11\52\6\0\3\52\1\0\10\52" +
			"\1\0\2\52\2\0\2\52\3\0\2\125\1\0\1\125" +
			"\7\0\1\125\56\0\6\4\2\0\1\51\3\4\1\0" +
			"\1\4\7\0\1\4\1\201\1\4\1\0\11\4\6\0" +
			"\3\4\1\0\10\4\1\0\2\4\2\0\2\4\7\131" +
			"\1\60\1\0\67\131\1\134\1\131\1\134\1\132\1\0" +
			"\5\131\1\134\61\131\1\57\1\131\1\57\1\132\1\0" +
			"\5\131\1\57\60\131\4\202\1\60\1\0\3\131\1\202" +
			"\1\131\1\202\11\131\1\202\5\131\3\202\15\131\1\202" +
			"\1\131\1\202\13\131\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\11\4" +
			"\6\0\3\4\1\0\2\4\1\203\5\4\1\0\2\4" +
			"\2\0\2\4\10\64\1\0\66\64\4\204\2\64\1\140" +
			"\2\64\1\204\1\141\1\204\11\64\1\204\5\64\3\204" +
			"\15\64\1\204\1\64\1\204\13\64\24\0\1\205\50\0" +
			"\6\4\2\0\1\51\3\4\1\0\1\4\7\0\1\4" +
			"\1\0\1\4\1\0\6\4\1\206\2\4\6\0\3\4" +
			"\1\0\10\4\1\0\2\4\2\0\2\4\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\207\1\0\11\4\6\0\3\4\1\0\10\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\7\4" +
			"\1\210\1\4\6\0\3\4\1\0\10\4\1\0\2\4" +
			"\2\0\2\4\1\0\6\4\2\0\1\51\3\4\1\0" +
			"\1\4\7\0\1\4\1\0\1\211\1\0\11\4\6\0" +
			"\3\4\1\0\10\4\1\0\2\4\2\0\2\4\1\0" +
			"\6\4\2\0\1\51\1\212\2\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\11\4\6\0\3\4\1\0" +
			"\10\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\7\4\1\213\1\4\6\0\3\4\1\0\10\4" +
			"\1\0\2\4\2\0\2\4\1\0\6\4\2\0\1\51" +
			"\3\4\1\0\1\4\7\0\1\4\1\0\1\4\1\0" +
			"\11\4\6\0\3\4\1\0\6\4\1\163\1\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\10\4" +
			"\1\145\6\0\3\4\1\0\10\4\1\0\2\4\2\0" +
			"\2\4\1\0\6\4\2\0\1\51\3\4\1\0\1\4" +
			"\7\0\1\4\1\0\1\214\1\0\11\4\6\0\3\4" +
			"\1\0\10\4\1\0\2\4\2\0\2\4\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\4\4\2\111\3\4\6\0\3\4\1\0" +
			"\10\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\2\215\7\4\6\0\3\4\1\0\10\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\2\216" +
			"\7\4\6\0\3\4\1\0\10\4\1\0\2\4\2\0" +
			"\2\4\1\0\6\4\2\0\1\51\3\4\1\0\1\4" +
			"\7\0\1\4\1\0\1\105\1\0\11\4\6\0\3\4" +
			"\1\0\3\4\1\217\4\4\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\7\4\1\163\1\220\6\0" +
			"\3\4\1\0\10\4\1\0\2\4\2\0\2\4\1\0" +
			"\6\4\2\0\1\51\1\4\1\165\1\4\1\0\1\4" +
			"\7\0\1\4\1\0\1\4\1\0\11\4\6\0\3\4" +
			"\1\0\4\4\1\165\3\4\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\105\1\0\11\4\6\0\3\4\1\0" +
			"\10\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\7\4\1\221\1\4\6\0\3\4\1\0\10\4" +
			"\1\0\2\4\2\0\2\4\1\0\6\4\2\0\1\51" +
			"\3\4\1\0\1\4\7\0\1\4\1\0\1\4\1\0" +
			"\2\105\7\4\6\0\3\4\1\0\10\4\1\0\2\4" +
			"\2\0\2\4\1\0\6\4\2\0\1\51\3\4\1\0" +
			"\1\4\7\0\1\4\1\0\1\4\1\0\2\4\2\222" +
			"\5\4\6\0\3\4\1\0\10\4\1\0\2\4\2\0" +
			"\2\4\10\40\1\0\13\40\1\0\12\40\1\0\14\40" +
			"\1\223\15\40\2\0\10\40\1\0\13\40\1\0\3\40" +
			"\1\170\6\40\1\0\32\40\56\0\1\224\47\0\1\172" +
			"\43\0\10\40\1\0\13\40\1\0\12\40\1\0\12\40" +
			"\1\225\17\40\2\0\10\40\1\0\13\40\1\0\2\40" +
			"\1\226\7\40\1\0\32\40\31\0\1\227\116\0\1\230" +
			"\24\0\4\231\5\0\1\231\1\0\1\231\11\0\1\231" +
			"\5\0\3\231\15\0\1\231\1\0\1\231\26\0\1\232" +
			"\45\0\1\232\12\0\3\131\4\233\1\60\1\0\3\131" +
			"\1\233\1\131\1\233\11\131\1\233\5\131\3\233\15\131" +
			"\1\233\1\131\1\233\13\131\1\0\6\4\2\0\1\51" +
			"\3\4\1\0\1\4\7\0\1\4\1\0\1\4\1\0" +
			"\2\4\2\234\5\4\6\0\3\4\1\0\10\4\1\0" +
			"\2\4\2\0\2\4\3\64\4\235\2\64\1\140\2\64" +
			"\1\235\1\141\1\235\11\64\1\235\5\64\3\235\15\64" +
			"\1\235\1\64\1\235\13\64\1\0\6\4\2\0\1\51" +
			"\1\4\1\236\1\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\11\4\6\0\3\4\1\0\4\4\1\236" +
			"\3\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\2\237\7\4\6\0\3\4\1\0\10\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\1\4" +
			"\1\240\1\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\11\4\6\0\3\4\1\0\4\4\1\240\3\4" +
			"\1\0\2\4\2\0\2\4\1\0\6\4\2\0\1\51" +
			"\3\4\1\0\1\4\7\0\1\4\1\0\1\4\1\0" +
			"\7\4\1\165\1\4\6\0\3\4\1\0\10\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\241\1\4\1\0\11\4" +
			"\6\0\3\4\1\0\10\4\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\6\4\1\216\2\4\6\0" +
			"\3\4\1\0\10\4\1\0\2\4\2\0\2\4\1\0" +
			"\6\4\2\0\1\51\3\4\1\0\1\4\7\0\1\4" +
			"\1\0\1\130\1\0\11\4\6\0\3\4\1\0\10\4" +
			"\1\0\2\4\2\0\2\4\1\0\6\4\2\0\1\51" +
			"\3\4\1\0\1\4\7\0\1\4\1\0\1\4\1\0" +
			"\11\4\6\0\3\4\1\0\1\4\1\242\6\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\243\1\0\11\4" +
			"\6\0\3\4\1\0\10\4\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\11\4\6\0\1\4\1\244" +
			"\1\4\1\0\10\4\1\0\2\4\2\0\2\4\1\0" +
			"\6\4\2\0\1\51\3\4\1\0\1\4\7\0\1\4" +
			"\1\0\1\4\1\0\11\4\6\0\3\4\1\0\2\4" +
			"\1\245\5\4\1\0\2\4\2\0\2\4\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\11\4\6\0\3\4\1\0\2\4\1\246" +
			"\5\4\1\0\2\4\2\0\2\4\10\40\1\0\13\40" +
			"\1\0\12\40\1\0\10\40\1\247\21\40\52\0\1\250" +
			"\23\0\10\40\1\0\13\40\1\0\12\40\1\0\2\40" +
			"\1\170\11\40\1\223\15\40\2\0\1\40\7\226\1\0" +
			"\1\40\3\226\1\40\1\226\1\40\1\226\2\40\1\226" +
			"\1\251\1\40\11\226\1\227\3\226\1\40\21\226\1\40" +
			"\3\226\1\40\2\227\1\0\1\227\1\251\4\227\1\251" +
			"\2\0\3\227\1\0\1\227\1\0\1\251\2\0\2\251" +
			"\1\0\1\227\1\251\1\227\1\251\11\227\1\0\4\251" +
			"\4\227\1\251\10\227\1\0\2\227\1\251\1\0\2\227" +
			"\42\0\1\172\11\0\1\224\22\0\4\252\5\0\1\252" +
			"\1\0\1\252\11\0\1\252\5\0\3\252\15\0\1\252" +
			"\1\0\1\252\53\0\1\253\33\0\3\131\4\254\1\60" +
			"\1\0\3\131\1\254\1\131\1\254\11\131\1\254\5\131" +
			"\3\254\15\131\1\254\1\131\1\254\13\131\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\11\4\6\0\3\4\1\0\6\4\1\255" +
			"\1\4\1\0\2\4\2\0\2\4\3\64\4\256\2\64" +
			"\1\140\2\64\1\256\1\141\1\256\11\64\1\256\5\64" +
			"\3\256\15\64\1\256\1\64\1\256\13\64\1\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\11\4\6\0\3\4\1\0\3\4\1\257" +
			"\4\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\11\4\6\0\2\4\1\260\1\0\10\4\1\0" +
			"\2\4\2\0\2\4\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\261\1\4\1\0\10\4" +
			"\1\262\6\0\3\4\1\0\10\4\1\0\2\4\2\0" +
			"\2\4\34\0\2\263\20\0\1\264\16\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\4" +
			"\1\0\11\4\6\0\1\105\2\4\1\0\10\4\1\0" +
			"\2\4\2\0\1\105\1\4\1\0\6\4\2\0\1\51" +
			"\1\4\1\105\1\4\1\0\1\4\7\0\1\4\1\0" +
			"\1\4\1\0\11\4\6\0\3\4\1\0\4\4\1\105" +
			"\3\4\1\0\2\4\2\0\2\4\1\0\6\4\2\0" +
			"\1\51\3\4\1\0\1\4\7\0\1\4\1\0\1\265" +
			"\1\0\11\4\6\0\3\4\1\0\10\4\1\0\2\4" +
			"\2\0\2\4\1\0\6\4\2\0\1\51\3\4\1\0" +
			"\1\4\7\0\1\4\1\0\1\4\1\0\11\4\6\0" +
			"\3\4\1\0\5\4\1\105\2\4\1\0\2\4\2\0" +
			"\2\4\1\0\6\4\2\0\1\51\3\4\1\0\1\4" +
			"\7\0\1\4\1\266\1\4\1\0\11\4\6\0\3\4" +
			"\1\0\10\4\1\0\2\4\2\0\2\4\10\40\1\0" +
			"\13\40\1\0\12\40\1\0\10\40\1\226\21\40\52\0" +
			"\1\227\26\0\4\4\5\0\1\4\1\0\1\4\11\0" +
			"\1\4\5\0\3\4\15\0\1\4\1\0\1\4\76\0" +
			"\1\267\10\0\3\131\4\57\1\60\1\0\3\131\1\57" +
			"\1\131\1\57\11\131\1\57\5\131\3\57\15\131\1\57" +
			"\1\131\1\57\13\131\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\4\1\0\6\4" +
			"\1\270\2\4\6\0\3\4\1\0\10\4\1\0\2\4" +
			"\2\0\2\4\3\64\4\13\2\64\1\140\2\64\1\13" +
			"\1\141\1\13\11\64\1\13\5\64\3\13\15\64\1\13" +
			"\1\64\1\13\13\64\1\0\6\4\2\0\1\51\3\4" +
			"\1\0\1\4\7\0\1\4\1\0\1\271\1\0\11\4" +
			"\6\0\3\4\1\0\10\4\1\0\2\4\2\0\2\4" +
			"\1\0\6\4\2\0\1\51\3\4\1\0\1\4\7\0" +
			"\1\4\1\0\1\4\1\0\11\4\6\0\1\4\1\105" +
			"\1\4\1\0\10\4\1\0\2\4\2\0\2\4\13\0" +
			"\1\232\25\0\1\272\7\0\1\273\1\0\1\274\5\0" +
			"\1\232\1\275\3\0\1\276\3\0\1\273\2\0\6\4" +
			"\2\0\1\51\3\4\1\0\1\4\7\0\1\4\1\277" +
			"\1\4\1\0\11\4\6\0\3\4\1\0\10\4\1\0" +
			"\2\4\2\0\2\4\30\0\1\300\122\0\1\301\15\0" +
			"\6\4\2\0\1\51\3\4\1\0\1\4\7\0\1\4" +
			"\1\0\1\4\1\0\2\4\2\105\5\4\6\0\3\4" +
			"\1\0\10\4\1\0\2\4\2\0\2\4\34\0\2\302" +
			"\2\0\1\303\63\0\1\304\44\0\6\4\2\0\1\51" +
			"\3\4\1\0\1\4\7\0\1\4\1\0\1\4\1\0" +
			"\2\305\7\4\6\0\3\4\1\0\10\4\1\0\2\4" +
			"\2\0\2\4\1\0\6\4\2\0\1\51\3\4\1\0" +
			"\1\4\7\0\1\4\1\0\1\4\1\0\11\4\6\0" +
			"\3\4\1\0\3\4\1\105\4\4\1\0\2\4\2\0" +
			"\2\4\40\0\1\306\73\0\1\307\46\0\1\310\45\0" +
			"\1\310\75\0\1\311\63\0\1\312\100\0\1\313\2\0" +
			"\1\314\52\0\1\315\43\0\1\316\111\0\1\317\77\0" +
			"\2\320\37\0\6\4\2\0\1\51\3\4\1\0\1\4" +
			"\7\0\1\4\1\0\1\4\1\0\2\257\7\4\6\0" +
			"\3\4\1\0\10\4\1\0\2\4\2\0\2\4\5\0" +
			"\1\321\6\0\1\321\120\0\1\322\105\0\1\323\105\0" +
			"\1\201\50\0\1\324\61\0\1\325\122\0\1\326\26\0" +
			"\1\327\74\0\1\330\45\0\1\330\44\0\2\331\122\0" +
			"\1\332\41\0\1\333\122\0\1\304\46\0\2\334\113\0" +
			"\1\335\33\0\1\336\45\0\1\336\100\0\1\337\46\0" +
			"\1\330\64\0\2\304\52\0\1\340\123\0\1\304\72\0" +
			"\1\304\103\0\1\341\20\0\1\341\6\0\1\342\6\0" +
			"\1\342\146\0\1\343\34\0\1\344\77\0\2\345\115\0" +
			"\1\346\55\0\1\267\115\0\1\347\66\0\1\264\30\0" +
			"\1\304\45\0\1\304\53\0\1\350\111\0\1\351\74\0" +
			"\1\304\101\0\1\267\5\0";

	private static int[] zzUnpackTrans() {
		int[] result = new int[12960];
		int offset = 0;
		offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackTrans(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			value--;
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/* error codes */
	private static final int ZZ_UNKNOWN_ERROR = 0;
	private static final int ZZ_NO_MATCH = 1;
	private static final int ZZ_PUSHBACK_2BIG = 2;

	/* error messages for the codes above */
	private static final String ZZ_ERROR_MSG[] = {
			"Unkown internal scanner error",
			"Error: could not match input",
			"Error: pushback value was too large"
	};

	/**
	 * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
	 */
	private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

	private static final String ZZ_ATTRIBUTE_PACKED_0 = "\2\0\1\11\5\1\1\11\4\1\1\11\7\1\1\11" +
			"\12\1\1\11\7\1\1\0\1\1\1\0\4\1\1\11" +
			"\1\0\4\1\1\11\1\0\23\1\1\0\2\1\2\0" +
			"\2\1\3\0\1\1\1\0\3\1\1\11\6\1\1\11" +
			"\1\1\1\0\23\1\1\11\2\1\2\0\2\1\4\0" +
			"\3\1\1\11\16\1\1\0\3\1\3\0\6\1\1\0" +
			"\6\1\4\0\5\1\1\0\1\1\2\0\1\1\2\0" +
			"\2\1\12\0\1\11\1\1\26\0\1\1\15\0";

	private static int[] zzUnpackAttribute() {
		int[] result = new int[233];
		int offset = 0;
		offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAttribute(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/** the input device */
	private java.io.Reader zzReader;

	/** the current state of the DFA */
	private int zzState;

	/** the current lexical state */
	private int zzLexicalState = YYINITIAL;

	/**
	 * this buffer contains the current text to be matched and is the source of the yytext() string
	 */
	private char zzBuffer[];

	/** the textposition at the last accepting state */
	private int zzMarkedPos;

	/** the textposition at the last state to be included in yytext */
	private int zzPushbackPos;

	/** the current text position in the buffer */
	private int zzCurrentPos;

	/** startRead marks the beginning of the yytext() string in the buffer */
	private int zzStartRead;

	/**
	 * endRead marks the last character in the buffer, that has been read from input
	 */
	private int zzEndRead;

	/** number of newlines encountered up to the start of the matched text */
	private int yyline;

	/** the number of characters up to the start of the matched text */
	private int yychar;

	/**
	 * the number of characters from the last newline up to the start of the matched text
	 */
	private int yycolumn;

	/**
	 * zzAtBOL == true <=> the scanner is currently at the beginning of a line
	 */
	private boolean zzAtBOL = true;

	/** zzAtEOF == true <=> the scanner is at the EOF */
	private boolean zzAtEOF;

	/* user code: */

	/**
	 * Constructor. This must be here because JFlex does not generate a no-parameter constructor.
	 */
	public MapToolScriptTokenMaker() {
	}

	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType
	 *            The token's type.
	 * @see #addToken(int, int, int)
	 */
	private void addHyperlinkToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start, end, tokenType, so, true);
	}

	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType
	 *            The token's type.
	 */
	private void addToken(int tokenType) {
		addToken(zzStartRead, zzMarkedPos - 1, tokenType);
	}

	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType
	 *            The token's type.
	 * @see #addHyperlinkToken(int, int, int)
	 */
	private void addToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start, end, tokenType, so, false);
	}

	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param array
	 *            The character array.
	 * @param start
	 *            The starting offset in the array.
	 * @param end
	 *            The ending offset in the array.
	 * @param tokenType
	 *            The token's type.
	 * @param startOffset
	 *            The offset in the document at which this token occurs.
	 * @param hyperlink
	 *            Whether this token is a hyperlink.
	 */
	public void addToken(char[] array, int start, int end, int tokenType,
			int startOffset, boolean hyperlink) {
		super.addToken(array, start, end, tokenType, startOffset, hyperlink);
		zzStartRead = zzMarkedPos;
	}

	/**
	 * {@inheritDoc}
	 */
	public String[] getLineCommentStartAndEnd(int languageIndex) {
		return null;
	}

	/**
	 * Returns the first token in the linked list of tokens generated from <code>text</code>. This method must be implemented by subclasses so they can correctly implement syntax highlighting.
	 *
	 * @param text
	 *            The text from which to get tokens.
	 * @param initialTokenType
	 *            The token type we should start with.
	 * @param startOffset
	 *            The offset into the document at which <code>text</code> starts.
	 * @return The first <code>Token</code> in a linked list representing the syntax highlighted text.
	 */
	public Token getTokenList(Segment text, int initialTokenType, int startOffset) {

		resetTokenList();
		this.offsetShift = -text.offset + startOffset;

		// Start off in the proper state.
		int state = Token.NULL;
		switch (initialTokenType) {
		case Token.COMMENT_MULTILINE:
			state = MLC;
			start = text.offset;
			break;

		/* No documentation comments */
		default:
			state = Token.NULL;
		}

		s = text;
		try {
			yyreset(zzReader);
			yybegin(state);
			return yylex();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new TokenImpl();
		}

	}

	/**
	 * Refills the input buffer.
	 *
	 * @return <code>true</code> if EOF was reached, otherwise <code>false</code>.
	 */
	private boolean zzRefill() {
		return zzCurrentPos >= s.offset + s.count;
	}

	/**
	 * Resets the scanner to read from a new input stream. Does not close the old reader.
	 *
	 * All internal variables are reset, the old input stream <b>cannot</b> be reused (internal buffer is discarded and lost). Lexical state is set to <tt>YY_INITIAL</tt>.
	 *
	 * @param reader
	 *            the new input stream
	 */
	public final void yyreset(Reader reader) {
		// 's' has been updated.
		zzBuffer = s.array;
		/*
		 * We replaced the line below with the two below it because zzRefill no longer "refills" the buffer (since the way we do it, it's always "full" the first time through, since it points to the
		 * segment's array). So, we assign zzEndRead here.
		 */
		// zzStartRead = zzEndRead = s.offset;
		zzStartRead = s.offset;
		zzEndRead = zzStartRead + s.count - 1;
		zzCurrentPos = zzMarkedPos = zzPushbackPos = s.offset;
		zzLexicalState = YYINITIAL;
		zzReader = reader;
		zzAtBOL = true;
		zzAtEOF = false;
	}

	/**
	 * Creates a new scanner There is also a java.io.InputStream version of this constructor.
	 *
	 * @param in
	 *            the java.io.Reader to read input from.
	 */
	public MapToolScriptTokenMaker(java.io.Reader in) {
		this.zzReader = in;
	}

	/**
	 * Creates a new scanner. There is also java.io.Reader version of this constructor.
	 *
	 * @param in
	 *            the java.io.Inputstream to read input from.
	 */
	public MapToolScriptTokenMaker(java.io.InputStream in) {
		this(new java.io.InputStreamReader(in));
	}

	/**
	 * Unpacks the compressed character translation table.
	 *
	 * @param packed
	 *            the packed character translation table
	 * @return the unpacked character translation table
	 */
	private static char[] zzUnpackCMap(String packed) {
		char[] map = new char[0x10000];
		int i = 0; /* index in packed string */
		int j = 0; /* index in unpacked array */
		while (i < 192) {
			int count = packed.charAt(i++);
			char value = packed.charAt(i++);
			do
				map[j++] = value;
			while (--count > 0);
		}
		return map;
	}

	/**
	 * Closes the input stream.
	 */
	public final void yyclose() throws java.io.IOException {
		zzAtEOF = true; /* indicate end of file */
		zzEndRead = zzStartRead; /* invalidate buffer */

		if (zzReader != null)
			zzReader.close();
	}

	/**
	 * Enters a new lexical state
	 *
	 * @param newState
	 *            the new lexical state
	 */
	public final void yybegin(int newState) {
		zzLexicalState = newState;
	}

	/**
	 * Returns the text matched by the current regular expression.
	 */
	public final String yytext() {
		return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
	}

	/**
	 * Returns the character at position <tt>pos</tt> from the matched text.
	 * 
	 * It is equivalent to yytext().charAt(pos), but faster
	 *
	 * @param pos
	 *            the position of the character to fetch. A value from 0 to yylength()-1.
	 *
	 * @return the character at position pos
	 */
	public final char yycharat(int pos) {
		return zzBuffer[zzStartRead + pos];
	}

	/**
	 * Returns the length of the matched text region.
	 */
	public final int yylength() {
		return zzMarkedPos - zzStartRead;
	}

	/**
	 * Reports an error that occured while scanning.
	 *
	 * In a wellformed scanner (no or only correct usage of yypushback(int) and a match-all fallback rule) this method will only be called with things that "Can't Possibly Happen". If this method is
	 * called, something is seriously wrong (e.g. a JFlex bug producing a faulty scanner etc.).
	 *
	 * Usual syntax/scanner level error handling should be done in error fallback rules.
	 *
	 * @param errorCode
	 *            the code of the errormessage to display
	 */
	private void zzScanError(int errorCode) {
		String message;
		try {
			message = ZZ_ERROR_MSG[errorCode];
		} catch (ArrayIndexOutOfBoundsException e) {
			message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
		}

		throw new Error(message);
	}

	/**
	 * Pushes the specified amount of characters back into the input stream.
	 *
	 * They will be read again by then next call of the scanning method
	 *
	 * @param number
	 *            the number of characters to be read again. This number must not be greater than yylength()!
	 */
	public void yypushback(int number) {
		if (number > yylength())
			zzScanError(ZZ_PUSHBACK_2BIG);

		zzMarkedPos -= number;
	}

	/**
	 * Resumes scanning until the next regular expression is matched, the end of input is encountered or an I/O-Error occurs.
	 *
	 * @return the next token
	 * @exception java.io.IOException
	 *                if any I/O-Error occurs
	 */
	public org.fife.ui.rsyntaxtextarea.Token yylex() throws java.io.IOException {
		int zzInput;
		int zzAction;

		// cached fields:
		int zzCurrentPosL;
		int zzMarkedPosL;
		int zzEndReadL = zzEndRead;
		char[] zzBufferL = zzBuffer;
		char[] zzCMapL = ZZ_CMAP;

		int[] zzTransL = ZZ_TRANS;
		int[] zzRowMapL = ZZ_ROWMAP;
		int[] zzAttrL = ZZ_ATTRIBUTE;

		while (true) {
			zzMarkedPosL = zzMarkedPos;

			zzAction = -1;

			zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

			zzState = zzLexicalState;

			zzForAction:
			{
				while (true) {

					if (zzCurrentPosL < zzEndReadL)
						zzInput = zzBufferL[zzCurrentPosL++];
					else if (zzAtEOF) {
						zzInput = YYEOF;
						break zzForAction;
					} else {
						// store back cached positions
						zzCurrentPos = zzCurrentPosL;
						zzMarkedPos = zzMarkedPosL;
						boolean eof = zzRefill();
						// get translated positions and possibly new buffer
						zzCurrentPosL = zzCurrentPos;
						zzMarkedPosL = zzMarkedPos;
						zzBufferL = zzBuffer;
						zzEndReadL = zzEndRead;
						if (eof) {
							zzInput = YYEOF;
							break zzForAction;
						} else {
							zzInput = zzBufferL[zzCurrentPosL++];
						}
					}
					int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
					if (zzNext == -1)
						break zzForAction;
					zzState = zzNext;

					int zzAttributes = zzAttrL[zzState];
					if ((zzAttributes & 1) == 1) {
						zzAction = zzState;
						zzMarkedPosL = zzCurrentPosL;
						if ((zzAttributes & 8) == 8)
							break zzForAction;
					}

				}
			}

			// store back cached position
			zzMarkedPos = zzMarkedPosL;

			switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
			case 4: {
				addNullToken();
				return firstToken;
			}
			case 24:
				break;
			case 17: {
				addToken(Token.LITERAL_CHAR);
			}
			case 25:
				break;
			case 7: {
				addToken(Token.WHITESPACE);
			}
			case 26:
				break;
			case 16: {
				addToken(Token.LITERAL_NUMBER_HEXADECIMAL);
			}
			case 27:
				break;
			case 18: {
				addToken(Token.ERROR_STRING_DOUBLE);
			}
			case 28:
				break;
			case 15: {
				addToken(Token.LITERAL_NUMBER_FLOAT);
			}
			case 29:
				break;
			case 5: {
				addToken(Token.RESERVED_WORD);
			}
			case 30:
				break;
			case 20: {
				start = zzMarkedPos - 4;
				yybegin(MLC);
			}
			case 31:
				break;
			case 9: {
				addToken(Token.SEPARATOR);
			}
			case 32:
				break;
			case 1: {
				addToken(Token.IDENTIFIER);
			}
			case 33:
				break;
			case 3: {
				addToken(Token.ERROR_CHAR);
				addNullToken();
				return firstToken;
			}
			case 34:
				break;
			case 6: {
				addToken(Token.ERROR_STRING_DOUBLE);
				addNullToken();
				return firstToken;
			}
			case 35:
				break;
			case 23: {
				addToken(Token.DATA_TYPE);
			}
			case 36:
				break;
			case 13: {
				addToken(Token.ERROR_CHAR);
			}
			case 37:
				break;
			case 21: {
				addToken(Token.LITERAL_BOOLEAN);
			}
			case 38:
				break;
			case 14: {
				addToken(Token.LITERAL_STRING_DOUBLE_QUOTE);
			}
			case 39:
				break;
			case 22: {
				int temp = zzStartRead;
				addToken(start, zzStartRead - 1, Token.COMMENT_MULTILINE);
				addHyperlinkToken(temp, zzMarkedPos - 1, Token.COMMENT_MULTILINE);
				start = zzMarkedPos;
			}
			case 40:
				break;
			case 12: {
				addToken(Token.ERROR_NUMBER_FORMAT);
			}
			case 41:
				break;
			case 19: {
				yybegin(YYINITIAL);
				addToken(start, zzStartRead + 3 - 1, Token.COMMENT_MULTILINE);
			}
			case 42:
				break;
			case 2: {
				addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
			}
			case 43:
				break;
			case 8: {
				addToken(Token.OPERATOR);
			}
			case 44:
				break;
			case 10: {
			}
			case 45:
				break;
			case 11: {
				addToken(start, zzStartRead - 1, Token.COMMENT_MULTILINE);
				return firstToken;
			}
			case 46:
				break;
			default:
				if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
					zzAtEOF = true;
					switch (zzLexicalState) {
					case YYINITIAL: {
						addNullToken();
						return firstToken;
					}
					case 234:
						break;
					case MLC: {
						addToken(start, zzStartRead - 1, Token.COMMENT_MULTILINE);
						return firstToken;
					}
					case 235:
						break;
					default:
						return null;
					}
				} else {
					zzScanError(ZZ_NO_MATCH);
				}
			}
		}
	}

}
