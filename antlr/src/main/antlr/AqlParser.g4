parser grammar AqlParser;

options { tokenVocab=AqlLexer; }

aqlStatement : expression EOF ;

expressions : expression (COMMA expression)* ;

expression : operator=NOT LPAREN expression RPAREN                              # notOperation
            | operator=EQ LPAREN left=expression COMMA right=expression RPAREN         # eqOperation
       //     | operator=NE LPAREN left=INTLIT COMMA right=INTLIT RPAREN        # neOperation
//            | operator=LT LPAREN left=INTLIT COMMA right=INTLIT RPAREN        # ltOperation
//            | operator=GT LPAREN left=INTLIT COMMA right=INTLIT RPAREN        # gtOperation
//            | operator=AND LPAREN expressions RPAREN                            # andOperation
//            | operator=OR  LPAREN expressions RPAREN                            # orOperation
  //          | LPAREN expression RPAREN                                          # parenExpression
            | INTLIT                                                            # intLiteral
            | STRLIT                                                            # strLiteral
            ;
