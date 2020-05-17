parser grammar AqlParser;

options { tokenVocab=AqlLexer; }

root : expression EOF ;

expressions : expression (COMMA expression)* ;

expression : operator=NOT LPAREN other=expression RPAREN                        # notOperation
            | operator=EQ LPAREN left=expression COMMA right=expression RPAREN  # eqOperation
            | operator=NE LPAREN left=expression COMMA right=expression RPAREN  # neOperation
            | operator=LT LPAREN left=expression COMMA right=expression RPAREN  # ltOperation
            | operator=GT LPAREN left=expression COMMA right=expression RPAREN  # gtOperation
            | operator=AND LPAREN expressions RPAREN                            # andOperation
            | operator=OR  LPAREN expressions RPAREN                            # orOperation
            | INTLIT                                                            # intLiteral
            | STRLIT                                                            # strLiteral
            ;
