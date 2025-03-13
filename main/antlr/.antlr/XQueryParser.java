// Generated from /Users/tangsheng/Desktop/CodeProjects/XQueryEvaluator/main/antlr/XQuery.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class XQueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, WS=22, DOC=23, STAR=24, DOT=25, 
		DOTDOT=26, TEXTOPEN=27, CLOSE=28, AT=29, EQ=30, EQVALUE=31, ISEQ=32, ISVALUE=33, 
		StringConstant=34, AND=35, OR=36, NOT=37, Name=38, COMMA=39;
	public static final int
		RULE_xquery = 0, RULE_forClause = 1, RULE_letClause = 2, RULE_whereClause = 3, 
		RULE_returnClause = 4, RULE_var = 5, RULE_cond = 6, RULE_stringConstant = 7, 
		RULE_xpath = 8, RULE_absolutePath = 9, RULE_relativePath = 10, RULE_filter = 11, 
		RULE_tagName = 12, RULE_attributeName = 13, RULE_fileName = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"xquery", "forClause", "letClause", "whereClause", "returnClause", "var", 
			"cond", "stringConstant", "xpath", "absolutePath", "relativePath", "filter", 
			"tagName", "attributeName", "fileName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "'/'", "'//'", "'<'", "'>'", "'{'", "'}'", "'</'", "'join'", 
			"'['", "']'", "'for'", "'in'", "'let'", "':='", "'where'", "'return'", 
			"'$'", "'empty'", "'some'", "'satisfies'", null, null, "'*'", "'.'", 
			"'..'", "'text('", "')'", "'@'", "'='", "'eq'", "'=='", "'is'", null, 
			"'and'", "'or'", "'not'", null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "WS", "DOC", 
			"STAR", "DOT", "DOTDOT", "TEXTOPEN", "CLOSE", "AT", "EQ", "EQVALUE", 
			"ISEQ", "ISVALUE", "StringConstant", "AND", "OR", "NOT", "Name", "COMMA"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "XQuery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XQueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class XqueryContext extends ParserRuleContext {
		public XqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xquery; }
	 
		public XqueryContext() { }
		public void copyFrom(XqueryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryDirectTagContext extends XqueryContext {
		public List<TagNameContext> tagName() {
			return getRuleContexts(TagNameContext.class);
		}
		public TagNameContext tagName(int i) {
			return getRuleContext(TagNameContext.class,i);
		}
		public XqueryContext xquery() {
			return getRuleContext(XqueryContext.class,0);
		}
		public XQueryDirectTagContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryParenthesesContext extends XqueryContext {
		public XqueryContext xquery() {
			return getRuleContext(XqueryContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(XQueryParser.CLOSE, 0); }
		public XQueryParenthesesContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryJoinContext extends XqueryContext {
		public List<XqueryContext> xquery() {
			return getRuleContexts(XqueryContext.class);
		}
		public XqueryContext xquery(int i) {
			return getRuleContext(XqueryContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(XQueryParser.COMMA, i);
		}
		public List<TerminalNode> Name() { return getTokens(XQueryParser.Name); }
		public TerminalNode Name(int i) {
			return getToken(XQueryParser.Name, i);
		}
		public TerminalNode CLOSE() { return getToken(XQueryParser.CLOSE, 0); }
		public XQueryJoinContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryVariableContext extends XqueryContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public XQueryVariableContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryFLWRContext extends XqueryContext {
		public ForClauseContext forClause() {
			return getRuleContext(ForClauseContext.class,0);
		}
		public ReturnClauseContext returnClause() {
			return getRuleContext(ReturnClauseContext.class,0);
		}
		public LetClauseContext letClause() {
			return getRuleContext(LetClauseContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public XQueryFLWRContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryConstantContext extends XqueryContext {
		public StringConstantContext stringConstant() {
			return getRuleContext(StringConstantContext.class,0);
		}
		public XQueryConstantContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryPathContext extends XqueryContext {
		public XqueryContext xquery() {
			return getRuleContext(XqueryContext.class,0);
		}
		public RelativePathContext relativePath() {
			return getRuleContext(RelativePathContext.class,0);
		}
		public XQueryPathContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryTagContext extends XqueryContext {
		public List<TagNameContext> tagName() {
			return getRuleContexts(TagNameContext.class);
		}
		public TagNameContext tagName(int i) {
			return getRuleContext(TagNameContext.class,i);
		}
		public XqueryContext xquery() {
			return getRuleContext(XqueryContext.class,0);
		}
		public XQueryTagContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryLetContext extends XqueryContext {
		public LetClauseContext letClause() {
			return getRuleContext(LetClauseContext.class,0);
		}
		public XqueryContext xquery() {
			return getRuleContext(XqueryContext.class,0);
		}
		public XQueryLetContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryAbsolutePathContext extends XqueryContext {
		public AbsolutePathContext absolutePath() {
			return getRuleContext(AbsolutePathContext.class,0);
		}
		public XQueryAbsolutePathContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryConcatContext extends XqueryContext {
		public List<XqueryContext> xquery() {
			return getRuleContexts(XqueryContext.class);
		}
		public XqueryContext xquery(int i) {
			return getRuleContext(XqueryContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(XQueryParser.COMMA, 0); }
		public XQueryConcatContext(XqueryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryDoubleSlashContext extends XqueryContext {
		public XqueryContext xquery() {
			return getRuleContext(XqueryContext.class,0);
		}
		public RelativePathContext relativePath() {
			return getRuleContext(RelativePathContext.class,0);
		}
		public XQueryDoubleSlashContext(XqueryContext ctx) { copyFrom(ctx); }
	}

	public final XqueryContext xquery() throws RecognitionException {
		return xquery(0);
	}

	private XqueryContext xquery(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		XqueryContext _localctx = new XqueryContext(_ctx, _parentState);
		XqueryContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_xquery, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				_localctx = new XQueryVariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(31);
				var();
				}
				break;
			case 2:
				{
				_localctx = new XQueryConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(32);
				stringConstant();
				}
				break;
			case 3:
				{
				_localctx = new XQueryAbsolutePathContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(33);
				absolutePath();
				}
				break;
			case 4:
				{
				_localctx = new XQueryParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(34);
				match(T__0);
				setState(35);
				xquery(0);
				setState(36);
				match(CLOSE);
				}
				break;
			case 5:
				{
				_localctx = new XQueryTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(38);
				match(T__3);
				setState(39);
				tagName();
				setState(40);
				match(T__4);
				setState(41);
				match(T__5);
				setState(42);
				xquery(0);
				setState(43);
				match(T__6);
				setState(44);
				match(T__7);
				setState(45);
				tagName();
				setState(46);
				match(T__4);
				}
				break;
			case 6:
				{
				_localctx = new XQueryDirectTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(48);
				match(T__3);
				setState(49);
				tagName();
				setState(50);
				match(T__4);
				setState(51);
				xquery(0);
				setState(52);
				match(T__7);
				setState(53);
				tagName();
				setState(54);
				match(T__4);
				}
				break;
			case 7:
				{
				_localctx = new XQueryFLWRContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(56);
				forClause();
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(57);
					letClause();
					}
				}

				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(60);
					whereClause();
					}
				}

				setState(63);
				returnClause();
				}
				break;
			case 8:
				{
				_localctx = new XQueryLetContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65);
				letClause();
				setState(66);
				xquery(2);
				}
				break;
			case 9:
				{
				_localctx = new XQueryJoinContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(68);
				match(T__8);
				setState(69);
				match(T__0);
				setState(70);
				xquery(0);
				setState(71);
				match(COMMA);
				setState(72);
				xquery(0);
				setState(73);
				match(COMMA);
				setState(74);
				match(T__9);
				setState(75);
				match(Name);
				setState(76);
				match(T__10);
				setState(77);
				match(COMMA);
				setState(78);
				match(T__9);
				setState(79);
				match(Name);
				setState(80);
				match(T__10);
				setState(81);
				match(CLOSE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(96);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(94);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new XQueryConcatContext(new XqueryContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_xquery);
						setState(85);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(86);
						match(COMMA);
						setState(87);
						xquery(9);
						}
						break;
					case 2:
						{
						_localctx = new XQueryPathContext(new XqueryContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_xquery);
						setState(88);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(89);
						match(T__1);
						setState(90);
						relativePath(0);
						}
						break;
					case 3:
						{
						_localctx = new XQueryDoubleSlashContext(new XqueryContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_xquery);
						setState(91);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(92);
						match(T__2);
						setState(93);
						relativePath(0);
						}
						break;
					}
					} 
				}
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForClauseContext extends ParserRuleContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<XqueryContext> xquery() {
			return getRuleContexts(XqueryContext.class);
		}
		public XqueryContext xquery(int i) {
			return getRuleContext(XqueryContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(XQueryParser.COMMA, i);
		}
		public ForClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forClause; }
	}

	public final ForClauseContext forClause() throws RecognitionException {
		ForClauseContext _localctx = new ForClauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_forClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__11);
			setState(100);
			var();
			setState(101);
			match(T__12);
			setState(102);
			xquery(0);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(103);
				match(COMMA);
				setState(104);
				var();
				setState(105);
				match(T__12);
				setState(106);
				xquery(0);
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LetClauseContext extends ParserRuleContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<XqueryContext> xquery() {
			return getRuleContexts(XqueryContext.class);
		}
		public XqueryContext xquery(int i) {
			return getRuleContext(XqueryContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(XQueryParser.COMMA, i);
		}
		public LetClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letClause; }
	}

	public final LetClauseContext letClause() throws RecognitionException {
		LetClauseContext _localctx = new LetClauseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_letClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__13);
			setState(114);
			var();
			setState(115);
			match(T__14);
			setState(116);
			xquery(0);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(117);
				match(COMMA);
				setState(118);
				var();
				setState(119);
				match(T__14);
				setState(120);
				xquery(0);
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhereClauseContext extends ParserRuleContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__15);
			setState(128);
			cond(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnClauseContext extends ParserRuleContext {
		public XqueryContext xquery() {
			return getRuleContext(XqueryContext.class,0);
		}
		public ReturnClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnClause; }
	}

	public final ReturnClauseContext returnClause() throws RecognitionException {
		ReturnClauseContext _localctx = new ReturnClauseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_returnClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(T__16);
			setState(131);
			xquery(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(XQueryParser.Name, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__17);
			setState(134);
			match(Name);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CondContext extends ParserRuleContext {
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
	 
		public CondContext() { }
		public void copyFrom(CondContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryCondParenthesesContext extends CondContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(XQueryParser.CLOSE, 0); }
		public XQueryCondParenthesesContext(CondContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryValueEqualContext extends CondContext {
		public List<XqueryContext> xquery() {
			return getRuleContexts(XqueryContext.class);
		}
		public XqueryContext xquery(int i) {
			return getRuleContext(XqueryContext.class,i);
		}
		public TerminalNode EQ() { return getToken(XQueryParser.EQ, 0); }
		public TerminalNode EQVALUE() { return getToken(XQueryParser.EQVALUE, 0); }
		public XQueryValueEqualContext(CondContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryEmptyContext extends CondContext {
		public XqueryContext xquery() {
			return getRuleContext(XqueryContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(XQueryParser.CLOSE, 0); }
		public XQueryEmptyContext(CondContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQuerySomeContext extends CondContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<XqueryContext> xquery() {
			return getRuleContexts(XqueryContext.class);
		}
		public XqueryContext xquery(int i) {
			return getRuleContext(XqueryContext.class,i);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(XQueryParser.COMMA, i);
		}
		public XQuerySomeContext(CondContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryCondAndContext extends CondContext {
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public TerminalNode AND() { return getToken(XQueryParser.AND, 0); }
		public XQueryCondAndContext(CondContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryIdentityEqualContext extends CondContext {
		public List<XqueryContext> xquery() {
			return getRuleContexts(XqueryContext.class);
		}
		public XqueryContext xquery(int i) {
			return getRuleContext(XqueryContext.class,i);
		}
		public TerminalNode ISEQ() { return getToken(XQueryParser.ISEQ, 0); }
		public TerminalNode ISVALUE() { return getToken(XQueryParser.ISVALUE, 0); }
		public XQueryIdentityEqualContext(CondContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryCondNotContext extends CondContext {
		public TerminalNode NOT() { return getToken(XQueryParser.NOT, 0); }
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public XQueryCondNotContext(CondContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XQueryCondOrContext extends CondContext {
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public TerminalNode OR() { return getToken(XQueryParser.OR, 0); }
		public XQueryCondOrContext(CondContext ctx) { copyFrom(ctx); }
	}

	public final CondContext cond() throws RecognitionException {
		return cond(0);
	}

	private CondContext cond(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CondContext _localctx = new CondContext(_ctx, _parentState);
		CondContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_cond, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				_localctx = new XQueryValueEqualContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(137);
				xquery(0);
				setState(138);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==EQVALUE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(139);
				xquery(0);
				}
				break;
			case 2:
				{
				_localctx = new XQueryIdentityEqualContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				xquery(0);
				setState(142);
				_la = _input.LA(1);
				if ( !(_la==ISEQ || _la==ISVALUE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(143);
				xquery(0);
				}
				break;
			case 3:
				{
				_localctx = new XQueryEmptyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(T__18);
				setState(146);
				match(T__0);
				setState(147);
				xquery(0);
				setState(148);
				match(CLOSE);
				}
				break;
			case 4:
				{
				_localctx = new XQuerySomeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150);
				match(T__19);
				setState(151);
				var();
				setState(152);
				match(T__12);
				setState(153);
				xquery(0);
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(154);
					match(COMMA);
					setState(155);
					var();
					setState(156);
					match(T__12);
					setState(157);
					xquery(0);
					}
					}
					setState(163);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(164);
				match(T__20);
				setState(165);
				cond(5);
				}
				break;
			case 5:
				{
				_localctx = new XQueryCondParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(167);
				match(T__0);
				setState(168);
				cond(0);
				setState(169);
				match(CLOSE);
				}
				break;
			case 6:
				{
				_localctx = new XQueryCondNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171);
				match(NOT);
				setState(172);
				cond(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(183);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(181);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new XQueryCondAndContext(new CondContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_cond);
						setState(175);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(176);
						match(AND);
						setState(177);
						cond(4);
						}
						break;
					case 2:
						{
						_localctx = new XQueryCondOrContext(new CondContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_cond);
						setState(178);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(179);
						match(OR);
						setState(180);
						cond(3);
						}
						break;
					}
					} 
				}
				setState(185);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringConstantContext extends ParserRuleContext {
		public TerminalNode StringConstant() { return getToken(XQueryParser.StringConstant, 0); }
		public StringConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringConstant; }
	}

	public final StringConstantContext stringConstant() throws RecognitionException {
		StringConstantContext _localctx = new StringConstantContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stringConstant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(StringConstant);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class XpathContext extends ParserRuleContext {
		public AbsolutePathContext absolutePath() {
			return getRuleContext(AbsolutePathContext.class,0);
		}
		public RelativePathContext relativePath() {
			return getRuleContext(RelativePathContext.class,0);
		}
		public XpathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xpath; }
	}

	public final XpathContext xpath() throws RecognitionException {
		XpathContext _localctx = new XpathContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_xpath);
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOC:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				absolutePath();
				}
				break;
			case T__0:
			case STAR:
			case DOT:
			case DOTDOT:
			case TEXTOPEN:
			case AT:
			case Name:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				relativePath(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AbsolutePathContext extends ParserRuleContext {
		public AbsolutePathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absolutePath; }
	 
		public AbsolutePathContext() { }
		public void copyFrom(AbsolutePathContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AbsoluteSlashContext extends AbsolutePathContext {
		public TerminalNode DOC() { return getToken(XQueryParser.DOC, 0); }
		public FileNameContext fileName() {
			return getRuleContext(FileNameContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(XQueryParser.CLOSE, 0); }
		public RelativePathContext relativePath() {
			return getRuleContext(RelativePathContext.class,0);
		}
		public AbsoluteSlashContext(AbsolutePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AbsoluteDoubleSlashContext extends AbsolutePathContext {
		public TerminalNode DOC() { return getToken(XQueryParser.DOC, 0); }
		public FileNameContext fileName() {
			return getRuleContext(FileNameContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(XQueryParser.CLOSE, 0); }
		public RelativePathContext relativePath() {
			return getRuleContext(RelativePathContext.class,0);
		}
		public AbsoluteDoubleSlashContext(AbsolutePathContext ctx) { copyFrom(ctx); }
	}

	public final AbsolutePathContext absolutePath() throws RecognitionException {
		AbsolutePathContext _localctx = new AbsolutePathContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_absolutePath);
		try {
			setState(206);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new AbsoluteSlashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				match(DOC);
				setState(193);
				match(T__0);
				setState(194);
				fileName();
				setState(195);
				match(CLOSE);
				setState(196);
				match(T__1);
				setState(197);
				relativePath(0);
				}
				break;
			case 2:
				_localctx = new AbsoluteDoubleSlashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				match(DOC);
				setState(200);
				match(T__0);
				setState(201);
				fileName();
				setState(202);
				match(CLOSE);
				setState(203);
				match(T__2);
				setState(204);
				relativePath(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelativePathContext extends ParserRuleContext {
		public RelativePathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relativePath; }
	 
		public RelativePathContext() { }
		public void copyFrom(RelativePathContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AllChildrenContext extends RelativePathContext {
		public TerminalNode STAR() { return getToken(XQueryParser.STAR, 0); }
		public AllChildrenContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TextFuncContext extends RelativePathContext {
		public TerminalNode TEXTOPEN() { return getToken(XQueryParser.TEXTOPEN, 0); }
		public TerminalNode CLOSE() { return getToken(XQueryParser.CLOSE, 0); }
		public TextFuncContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TagNameMatchContext extends RelativePathContext {
		public TagNameContext tagName() {
			return getRuleContext(TagNameContext.class,0);
		}
		public TagNameMatchContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParentContext extends RelativePathContext {
		public TerminalNode DOTDOT() { return getToken(XQueryParser.DOTDOT, 0); }
		public ParentContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AttributeContext extends RelativePathContext {
		public TerminalNode AT() { return getToken(XQueryParser.AT, 0); }
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelfContext extends RelativePathContext {
		public TerminalNode DOT() { return getToken(XQueryParser.DOT, 0); }
		public SelfContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RpConcatContext extends RelativePathContext {
		public List<RelativePathContext> relativePath() {
			return getRuleContexts(RelativePathContext.class);
		}
		public RelativePathContext relativePath(int i) {
			return getRuleContext(RelativePathContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(XQueryParser.COMMA, 0); }
		public RpConcatContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RpGroupingContext extends RelativePathContext {
		public RelativePathContext relativePath() {
			return getRuleContext(RelativePathContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(XQueryParser.CLOSE, 0); }
		public RpGroupingContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RpSlashContext extends RelativePathContext {
		public List<RelativePathContext> relativePath() {
			return getRuleContexts(RelativePathContext.class);
		}
		public RelativePathContext relativePath(int i) {
			return getRuleContext(RelativePathContext.class,i);
		}
		public RpSlashContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RpDoubleSlashContext extends RelativePathContext {
		public List<RelativePathContext> relativePath() {
			return getRuleContexts(RelativePathContext.class);
		}
		public RelativePathContext relativePath(int i) {
			return getRuleContext(RelativePathContext.class,i);
		}
		public RpDoubleSlashContext(RelativePathContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RpFilterContext extends RelativePathContext {
		public RelativePathContext relativePath() {
			return getRuleContext(RelativePathContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public RpFilterContext(RelativePathContext ctx) { copyFrom(ctx); }
	}

	public final RelativePathContext relativePath() throws RecognitionException {
		return relativePath(0);
	}

	private RelativePathContext relativePath(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RelativePathContext _localctx = new RelativePathContext(_ctx, _parentState);
		RelativePathContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_relativePath, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Name:
				{
				_localctx = new TagNameMatchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(209);
				tagName();
				}
				break;
			case STAR:
				{
				_localctx = new AllChildrenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(210);
				match(STAR);
				}
				break;
			case DOT:
				{
				_localctx = new SelfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(211);
				match(DOT);
				}
				break;
			case DOTDOT:
				{
				_localctx = new ParentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(212);
				match(DOTDOT);
				}
				break;
			case TEXTOPEN:
				{
				_localctx = new TextFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(213);
				match(TEXTOPEN);
				setState(214);
				match(CLOSE);
				}
				break;
			case AT:
				{
				_localctx = new AttributeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(215);
				match(AT);
				setState(216);
				attributeName();
				}
				break;
			case T__0:
				{
				_localctx = new RpGroupingContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(217);
				match(T__0);
				setState(218);
				relativePath(0);
				setState(219);
				match(CLOSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(239);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(237);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new RpSlashContext(new RelativePathContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_relativePath);
						setState(223);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(224);
						match(T__1);
						setState(225);
						relativePath(5);
						}
						break;
					case 2:
						{
						_localctx = new RpDoubleSlashContext(new RelativePathContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_relativePath);
						setState(226);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(227);
						match(T__2);
						setState(228);
						relativePath(4);
						}
						break;
					case 3:
						{
						_localctx = new RpConcatContext(new RelativePathContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_relativePath);
						setState(229);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(230);
						match(COMMA);
						setState(231);
						relativePath(2);
						}
						break;
					case 4:
						{
						_localctx = new RpFilterContext(new RelativePathContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_relativePath);
						setState(232);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(233);
						match(T__9);
						setState(234);
						filter(0);
						setState(235);
						match(T__10);
						}
						break;
					}
					} 
				}
				setState(241);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FilterContext extends ParserRuleContext {
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
	 
		public FilterContext() { }
		public void copyFrom(FilterContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndFilterContext extends FilterContext {
		public List<FilterContext> filter() {
			return getRuleContexts(FilterContext.class);
		}
		public FilterContext filter(int i) {
			return getRuleContext(FilterContext.class,i);
		}
		public TerminalNode AND() { return getToken(XQueryParser.AND, 0); }
		public AndFilterContext(FilterContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotFilterContext extends FilterContext {
		public TerminalNode NOT() { return getToken(XQueryParser.NOT, 0); }
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public NotFilterContext(FilterContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityFilterContext extends FilterContext {
		public List<RelativePathContext> relativePath() {
			return getRuleContexts(RelativePathContext.class);
		}
		public RelativePathContext relativePath(int i) {
			return getRuleContext(RelativePathContext.class,i);
		}
		public TerminalNode EQ() { return getToken(XQueryParser.EQ, 0); }
		public TerminalNode EQVALUE() { return getToken(XQueryParser.EQVALUE, 0); }
		public EqualityFilterContext(FilterContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringFilterContext extends FilterContext {
		public RelativePathContext relativePath() {
			return getRuleContext(RelativePathContext.class,0);
		}
		public TerminalNode EQ() { return getToken(XQueryParser.EQ, 0); }
		public TerminalNode StringConstant() { return getToken(XQueryParser.StringConstant, 0); }
		public StringFilterContext(FilterContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FilterGroupingContext extends FilterContext {
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(XQueryParser.CLOSE, 0); }
		public FilterGroupingContext(FilterContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrFilterContext extends FilterContext {
		public List<FilterContext> filter() {
			return getRuleContexts(FilterContext.class);
		}
		public FilterContext filter(int i) {
			return getRuleContext(FilterContext.class,i);
		}
		public TerminalNode OR() { return getToken(XQueryParser.OR, 0); }
		public OrFilterContext(FilterContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RpInFilterContext extends FilterContext {
		public RelativePathContext relativePath() {
			return getRuleContext(RelativePathContext.class,0);
		}
		public RpInFilterContext(FilterContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentityFilterContext extends FilterContext {
		public List<RelativePathContext> relativePath() {
			return getRuleContexts(RelativePathContext.class);
		}
		public RelativePathContext relativePath(int i) {
			return getRuleContext(RelativePathContext.class,i);
		}
		public TerminalNode ISEQ() { return getToken(XQueryParser.ISEQ, 0); }
		public TerminalNode ISVALUE() { return getToken(XQueryParser.ISVALUE, 0); }
		public IdentityFilterContext(FilterContext ctx) { copyFrom(ctx); }
	}

	public final FilterContext filter() throws RecognitionException {
		return filter(0);
	}

	private FilterContext filter(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FilterContext _localctx = new FilterContext(_ctx, _parentState);
		FilterContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_filter, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				_localctx = new RpInFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(243);
				relativePath(0);
				}
				break;
			case 2:
				{
				_localctx = new EqualityFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(244);
				relativePath(0);
				setState(245);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==EQVALUE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(246);
				relativePath(0);
				}
				break;
			case 3:
				{
				_localctx = new IdentityFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(248);
				relativePath(0);
				setState(249);
				_la = _input.LA(1);
				if ( !(_la==ISEQ || _la==ISVALUE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(250);
				relativePath(0);
				}
				break;
			case 4:
				{
				_localctx = new StringFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(252);
				relativePath(0);
				setState(253);
				match(EQ);
				setState(254);
				match(StringConstant);
				}
				break;
			case 5:
				{
				_localctx = new FilterGroupingContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(256);
				match(T__0);
				setState(257);
				filter(0);
				setState(258);
				match(CLOSE);
				}
				break;
			case 6:
				{
				_localctx = new NotFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(260);
				match(NOT);
				setState(261);
				filter(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(272);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(270);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new AndFilterContext(new FilterContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filter);
						setState(264);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(265);
						match(AND);
						setState(266);
						filter(4);
						}
						break;
					case 2:
						{
						_localctx = new OrFilterContext(new FilterContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filter);
						setState(267);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(268);
						match(OR);
						setState(269);
						filter(3);
						}
						break;
					}
					} 
				}
				setState(274);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TagNameContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(XQueryParser.Name, 0); }
		public TagNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagName; }
	}

	public final TagNameContext tagName() throws RecognitionException {
		TagNameContext _localctx = new TagNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_tagName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(Name);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttributeNameContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(XQueryParser.Name, 0); }
		public AttributeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeName; }
	}

	public final AttributeNameContext attributeName() throws RecognitionException {
		AttributeNameContext _localctx = new AttributeNameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_attributeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(Name);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileNameContext extends ParserRuleContext {
		public TerminalNode StringConstant() { return getToken(XQueryParser.StringConstant, 0); }
		public FileNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileName; }
	}

	public final FileNameContext fileName() throws RecognitionException {
		FileNameContext _localctx = new FileNameContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fileName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(StringConstant);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return xquery_sempred((XqueryContext)_localctx, predIndex);
		case 6:
			return cond_sempred((CondContext)_localctx, predIndex);
		case 10:
			return relativePath_sempred((RelativePathContext)_localctx, predIndex);
		case 11:
			return filter_sempred((FilterContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean xquery_sempred(XqueryContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		}
		return true;
	}
	private boolean cond_sempred(CondContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean relativePath_sempred(RelativePathContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 4);
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 1);
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean filter_sempred(FilterContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\'\u011a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0003\u0000;\b\u0000\u0001\u0000\u0003\u0000"+
		">\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000T\b\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0005\u0000_\b\u0000\n\u0000\f\u0000b\t\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001m\b\u0001\n\u0001\f\u0001"+
		"p\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002{\b\u0002"+
		"\n\u0002\f\u0002~\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00a0\b\u0006"+
		"\n\u0006\f\u0006\u00a3\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u00ae\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006\u00b6\b\u0006\n\u0006\f\u0006\u00b9\t\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0003\b\u00bf\b\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u00cf\b\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00de\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00ee"+
		"\b\n\n\n\f\n\u00f1\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0107\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0005\u000b\u010f\b\u000b\n\u000b\f\u000b\u0112\t\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0000\u0004\u0000"+
		"\f\u0014\u0016\u000f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u0000\u0002\u0001\u0000\u001e\u001f\u0001\u0000"+
		" !\u0134\u0000S\u0001\u0000\u0000\u0000\u0002c\u0001\u0000\u0000\u0000"+
		"\u0004q\u0001\u0000\u0000\u0000\u0006\u007f\u0001\u0000\u0000\u0000\b"+
		"\u0082\u0001\u0000\u0000\u0000\n\u0085\u0001\u0000\u0000\u0000\f\u00ad"+
		"\u0001\u0000\u0000\u0000\u000e\u00ba\u0001\u0000\u0000\u0000\u0010\u00be"+
		"\u0001\u0000\u0000\u0000\u0012\u00ce\u0001\u0000\u0000\u0000\u0014\u00dd"+
		"\u0001\u0000\u0000\u0000\u0016\u0106\u0001\u0000\u0000\u0000\u0018\u0113"+
		"\u0001\u0000\u0000\u0000\u001a\u0115\u0001\u0000\u0000\u0000\u001c\u0117"+
		"\u0001\u0000\u0000\u0000\u001e\u001f\u0006\u0000\uffff\uffff\u0000\u001f"+
		"T\u0003\n\u0005\u0000 T\u0003\u000e\u0007\u0000!T\u0003\u0012\t\u0000"+
		"\"#\u0005\u0001\u0000\u0000#$\u0003\u0000\u0000\u0000$%\u0005\u001c\u0000"+
		"\u0000%T\u0001\u0000\u0000\u0000&\'\u0005\u0004\u0000\u0000\'(\u0003\u0018"+
		"\f\u0000()\u0005\u0005\u0000\u0000)*\u0005\u0006\u0000\u0000*+\u0003\u0000"+
		"\u0000\u0000+,\u0005\u0007\u0000\u0000,-\u0005\b\u0000\u0000-.\u0003\u0018"+
		"\f\u0000./\u0005\u0005\u0000\u0000/T\u0001\u0000\u0000\u000001\u0005\u0004"+
		"\u0000\u000012\u0003\u0018\f\u000023\u0005\u0005\u0000\u000034\u0003\u0000"+
		"\u0000\u000045\u0005\b\u0000\u000056\u0003\u0018\f\u000067\u0005\u0005"+
		"\u0000\u00007T\u0001\u0000\u0000\u00008:\u0003\u0002\u0001\u00009;\u0003"+
		"\u0004\u0002\u0000:9\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000"+
		";=\u0001\u0000\u0000\u0000<>\u0003\u0006\u0003\u0000=<\u0001\u0000\u0000"+
		"\u0000=>\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?@\u0003\b\u0004"+
		"\u0000@T\u0001\u0000\u0000\u0000AB\u0003\u0004\u0002\u0000BC\u0003\u0000"+
		"\u0000\u0002CT\u0001\u0000\u0000\u0000DE\u0005\t\u0000\u0000EF\u0005\u0001"+
		"\u0000\u0000FG\u0003\u0000\u0000\u0000GH\u0005\'\u0000\u0000HI\u0003\u0000"+
		"\u0000\u0000IJ\u0005\'\u0000\u0000JK\u0005\n\u0000\u0000KL\u0005&\u0000"+
		"\u0000LM\u0005\u000b\u0000\u0000MN\u0005\'\u0000\u0000NO\u0005\n\u0000"+
		"\u0000OP\u0005&\u0000\u0000PQ\u0005\u000b\u0000\u0000QR\u0005\u001c\u0000"+
		"\u0000RT\u0001\u0000\u0000\u0000S\u001e\u0001\u0000\u0000\u0000S \u0001"+
		"\u0000\u0000\u0000S!\u0001\u0000\u0000\u0000S\"\u0001\u0000\u0000\u0000"+
		"S&\u0001\u0000\u0000\u0000S0\u0001\u0000\u0000\u0000S8\u0001\u0000\u0000"+
		"\u0000SA\u0001\u0000\u0000\u0000SD\u0001\u0000\u0000\u0000T`\u0001\u0000"+
		"\u0000\u0000UV\n\b\u0000\u0000VW\u0005\'\u0000\u0000W_\u0003\u0000\u0000"+
		"\tXY\n\u0007\u0000\u0000YZ\u0005\u0002\u0000\u0000Z_\u0003\u0014\n\u0000"+
		"[\\\n\u0006\u0000\u0000\\]\u0005\u0003\u0000\u0000]_\u0003\u0014\n\u0000"+
		"^U\u0001\u0000\u0000\u0000^X\u0001\u0000\u0000\u0000^[\u0001\u0000\u0000"+
		"\u0000_b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000"+
		"\u0000\u0000a\u0001\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000"+
		"cd\u0005\f\u0000\u0000de\u0003\n\u0005\u0000ef\u0005\r\u0000\u0000fn\u0003"+
		"\u0000\u0000\u0000gh\u0005\'\u0000\u0000hi\u0003\n\u0005\u0000ij\u0005"+
		"\r\u0000\u0000jk\u0003\u0000\u0000\u0000km\u0001\u0000\u0000\u0000lg\u0001"+
		"\u0000\u0000\u0000mp\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000o\u0003\u0001\u0000\u0000\u0000pn\u0001\u0000"+
		"\u0000\u0000qr\u0005\u000e\u0000\u0000rs\u0003\n\u0005\u0000st\u0005\u000f"+
		"\u0000\u0000t|\u0003\u0000\u0000\u0000uv\u0005\'\u0000\u0000vw\u0003\n"+
		"\u0005\u0000wx\u0005\u000f\u0000\u0000xy\u0003\u0000\u0000\u0000y{\u0001"+
		"\u0000\u0000\u0000zu\u0001\u0000\u0000\u0000{~\u0001\u0000\u0000\u0000"+
		"|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u0005\u0001\u0000"+
		"\u0000\u0000~|\u0001\u0000\u0000\u0000\u007f\u0080\u0005\u0010\u0000\u0000"+
		"\u0080\u0081\u0003\f\u0006\u0000\u0081\u0007\u0001\u0000\u0000\u0000\u0082"+
		"\u0083\u0005\u0011\u0000\u0000\u0083\u0084\u0003\u0000\u0000\u0000\u0084"+
		"\t\u0001\u0000\u0000\u0000\u0085\u0086\u0005\u0012\u0000\u0000\u0086\u0087"+
		"\u0005&\u0000\u0000\u0087\u000b\u0001\u0000\u0000\u0000\u0088\u0089\u0006"+
		"\u0006\uffff\uffff\u0000\u0089\u008a\u0003\u0000\u0000\u0000\u008a\u008b"+
		"\u0007\u0000\u0000\u0000\u008b\u008c\u0003\u0000\u0000\u0000\u008c\u00ae"+
		"\u0001\u0000\u0000\u0000\u008d\u008e\u0003\u0000\u0000\u0000\u008e\u008f"+
		"\u0007\u0001\u0000\u0000\u008f\u0090\u0003\u0000\u0000\u0000\u0090\u00ae"+
		"\u0001\u0000\u0000\u0000\u0091\u0092\u0005\u0013\u0000\u0000\u0092\u0093"+
		"\u0005\u0001\u0000\u0000\u0093\u0094\u0003\u0000\u0000\u0000\u0094\u0095"+
		"\u0005\u001c\u0000\u0000\u0095\u00ae\u0001\u0000\u0000\u0000\u0096\u0097"+
		"\u0005\u0014\u0000\u0000\u0097\u0098\u0003\n\u0005\u0000\u0098\u0099\u0005"+
		"\r\u0000\u0000\u0099\u00a1\u0003\u0000\u0000\u0000\u009a\u009b\u0005\'"+
		"\u0000\u0000\u009b\u009c\u0003\n\u0005\u0000\u009c\u009d\u0005\r\u0000"+
		"\u0000\u009d\u009e\u0003\u0000\u0000\u0000\u009e\u00a0\u0001\u0000\u0000"+
		"\u0000\u009f\u009a\u0001\u0000\u0000\u0000\u00a0\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a4\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a5\u0005\u0015\u0000\u0000\u00a5\u00a6\u0003\f\u0006\u0005"+
		"\u00a6\u00ae\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005\u0001\u0000\u0000"+
		"\u00a8\u00a9\u0003\f\u0006\u0000\u00a9\u00aa\u0005\u001c\u0000\u0000\u00aa"+
		"\u00ae\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005%\u0000\u0000\u00ac\u00ae"+
		"\u0003\f\u0006\u0001\u00ad\u0088\u0001\u0000\u0000\u0000\u00ad\u008d\u0001"+
		"\u0000\u0000\u0000\u00ad\u0091\u0001\u0000\u0000\u0000\u00ad\u0096\u0001"+
		"\u0000\u0000\u0000\u00ad\u00a7\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ae\u00b7\u0001\u0000\u0000\u0000\u00af\u00b0\n\u0003"+
		"\u0000\u0000\u00b0\u00b1\u0005#\u0000\u0000\u00b1\u00b6\u0003\f\u0006"+
		"\u0004\u00b2\u00b3\n\u0002\u0000\u0000\u00b3\u00b4\u0005$\u0000\u0000"+
		"\u00b4\u00b6\u0003\f\u0006\u0003\u00b5\u00af\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b6\u00b9\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8"+
		"\r\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bb"+
		"\u0005\"\u0000\u0000\u00bb\u000f\u0001\u0000\u0000\u0000\u00bc\u00bf\u0003"+
		"\u0012\t\u0000\u00bd\u00bf\u0003\u0014\n\u0000\u00be\u00bc\u0001\u0000"+
		"\u0000\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00bf\u0011\u0001\u0000"+
		"\u0000\u0000\u00c0\u00c1\u0005\u0017\u0000\u0000\u00c1\u00c2\u0005\u0001"+
		"\u0000\u0000\u00c2\u00c3\u0003\u001c\u000e\u0000\u00c3\u00c4\u0005\u001c"+
		"\u0000\u0000\u00c4\u00c5\u0005\u0002\u0000\u0000\u00c5\u00c6\u0003\u0014"+
		"\n\u0000\u00c6\u00cf\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005\u0017\u0000"+
		"\u0000\u00c8\u00c9\u0005\u0001\u0000\u0000\u00c9\u00ca\u0003\u001c\u000e"+
		"\u0000\u00ca\u00cb\u0005\u001c\u0000\u0000\u00cb\u00cc\u0005\u0003\u0000"+
		"\u0000\u00cc\u00cd\u0003\u0014\n\u0000\u00cd\u00cf\u0001\u0000\u0000\u0000"+
		"\u00ce\u00c0\u0001\u0000\u0000\u0000\u00ce\u00c7\u0001\u0000\u0000\u0000"+
		"\u00cf\u0013\u0001\u0000\u0000\u0000\u00d0\u00d1\u0006\n\uffff\uffff\u0000"+
		"\u00d1\u00de\u0003\u0018\f\u0000\u00d2\u00de\u0005\u0018\u0000\u0000\u00d3"+
		"\u00de\u0005\u0019\u0000\u0000\u00d4\u00de\u0005\u001a\u0000\u0000\u00d5"+
		"\u00d6\u0005\u001b\u0000\u0000\u00d6\u00de\u0005\u001c\u0000\u0000\u00d7"+
		"\u00d8\u0005\u001d\u0000\u0000\u00d8\u00de\u0003\u001a\r\u0000\u00d9\u00da"+
		"\u0005\u0001\u0000\u0000\u00da\u00db\u0003\u0014\n\u0000\u00db\u00dc\u0005"+
		"\u001c\u0000\u0000\u00dc\u00de\u0001\u0000\u0000\u0000\u00dd\u00d0\u0001"+
		"\u0000\u0000\u0000\u00dd\u00d2\u0001\u0000\u0000\u0000\u00dd\u00d3\u0001"+
		"\u0000\u0000\u0000\u00dd\u00d4\u0001\u0000\u0000\u0000\u00dd\u00d5\u0001"+
		"\u0000\u0000\u0000\u00dd\u00d7\u0001\u0000\u0000\u0000\u00dd\u00d9\u0001"+
		"\u0000\u0000\u0000\u00de\u00ef\u0001\u0000\u0000\u0000\u00df\u00e0\n\u0004"+
		"\u0000\u0000\u00e0\u00e1\u0005\u0002\u0000\u0000\u00e1\u00ee\u0003\u0014"+
		"\n\u0005\u00e2\u00e3\n\u0003\u0000\u0000\u00e3\u00e4\u0005\u0003\u0000"+
		"\u0000\u00e4\u00ee\u0003\u0014\n\u0004\u00e5\u00e6\n\u0001\u0000\u0000"+
		"\u00e6\u00e7\u0005\'\u0000\u0000\u00e7\u00ee\u0003\u0014\n\u0002\u00e8"+
		"\u00e9\n\u0002\u0000\u0000\u00e9\u00ea\u0005\n\u0000\u0000\u00ea\u00eb"+
		"\u0003\u0016\u000b\u0000\u00eb\u00ec\u0005\u000b\u0000\u0000\u00ec\u00ee"+
		"\u0001\u0000\u0000\u0000\u00ed\u00df\u0001\u0000\u0000\u0000\u00ed\u00e2"+
		"\u0001\u0000\u0000\u0000\u00ed\u00e5\u0001\u0000\u0000\u0000\u00ed\u00e8"+
		"\u0001\u0000\u0000\u0000\u00ee\u00f1\u0001\u0000\u0000\u0000\u00ef\u00ed"+
		"\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u0015"+
		"\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f3"+
		"\u0006\u000b\uffff\uffff\u0000\u00f3\u0107\u0003\u0014\n\u0000\u00f4\u00f5"+
		"\u0003\u0014\n\u0000\u00f5\u00f6\u0007\u0000\u0000\u0000\u00f6\u00f7\u0003"+
		"\u0014\n\u0000\u00f7\u0107\u0001\u0000\u0000\u0000\u00f8\u00f9\u0003\u0014"+
		"\n\u0000\u00f9\u00fa\u0007\u0001\u0000\u0000\u00fa\u00fb\u0003\u0014\n"+
		"\u0000\u00fb\u0107\u0001\u0000\u0000\u0000\u00fc\u00fd\u0003\u0014\n\u0000"+
		"\u00fd\u00fe\u0005\u001e\u0000\u0000\u00fe\u00ff\u0005\"\u0000\u0000\u00ff"+
		"\u0107\u0001\u0000\u0000\u0000\u0100\u0101\u0005\u0001\u0000\u0000\u0101"+
		"\u0102\u0003\u0016\u000b\u0000\u0102\u0103\u0005\u001c\u0000\u0000\u0103"+
		"\u0107\u0001\u0000\u0000\u0000\u0104\u0105\u0005%\u0000\u0000\u0105\u0107"+
		"\u0003\u0016\u000b\u0001\u0106\u00f2\u0001\u0000\u0000\u0000\u0106\u00f4"+
		"\u0001\u0000\u0000\u0000\u0106\u00f8\u0001\u0000\u0000\u0000\u0106\u00fc"+
		"\u0001\u0000\u0000\u0000\u0106\u0100\u0001\u0000\u0000\u0000\u0106\u0104"+
		"\u0001\u0000\u0000\u0000\u0107\u0110\u0001\u0000\u0000\u0000\u0108\u0109"+
		"\n\u0003\u0000\u0000\u0109\u010a\u0005#\u0000\u0000\u010a\u010f\u0003"+
		"\u0016\u000b\u0004\u010b\u010c\n\u0002\u0000\u0000\u010c\u010d\u0005$"+
		"\u0000\u0000\u010d\u010f\u0003\u0016\u000b\u0003\u010e\u0108\u0001\u0000"+
		"\u0000\u0000\u010e\u010b\u0001\u0000\u0000\u0000\u010f\u0112\u0001\u0000"+
		"\u0000\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000"+
		"\u0000\u0000\u0111\u0017\u0001\u0000\u0000\u0000\u0112\u0110\u0001\u0000"+
		"\u0000\u0000\u0113\u0114\u0005&\u0000\u0000\u0114\u0019\u0001\u0000\u0000"+
		"\u0000\u0115\u0116\u0005&\u0000\u0000\u0116\u001b\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0005\"\u0000\u0000\u0118\u001d\u0001\u0000\u0000\u0000\u0013"+
		":=S^`n|\u00a1\u00ad\u00b5\u00b7\u00be\u00ce\u00dd\u00ed\u00ef\u0106\u010e"+
		"\u0110";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}