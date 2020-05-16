lexer grammar AqlLexer;

// EOF is implicitly known

// Whitespace
WS                  : [\t ]+ -> skip ;  // skip means that we do not want to consider whitespaces in the parser

// Keywords
EQ                  : 'eq' ;
NE                  : 'ne' ;
LT                  : 'lt' ;
GT                  : 'gt' ;
OR                  : 'or' ;
AND                 : 'and' ;
NOT                 : 'not' ;

// Operators
COMMA              : ',' ;
PLUS               : '+' ;
MINUS              : '-' ;
ASTERISK           : '*' ;
DIVISION           : '/' ;
ASSIGN             : '=' ;
LPAREN             : '(' ;
RPAREN             : ')' ;

// Literals
INTLIT             : '0'|[1-9][0-9]* ;
STRLIT             : [-_\\.a-z0-9]+ ;
