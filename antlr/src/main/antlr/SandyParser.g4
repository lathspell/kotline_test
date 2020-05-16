parser grammar SandyParser;

options { tokenVocab=SandyLexer; }

sandyFile : lines=line+ ;

line      : statement (NEWLINE | EOF) ;

statement : varDeclaration # varDeclarationStatement
          | assignment     # assignmentStatement
          | print          # printStatement
          ;

print : PRINT LPAREN expression RPAREN ;

varDeclaration : VAR assignment ;

assignment : ID ASSIGN expression ;

expression : left=expression operator=(DIVISION|ASTERISK) right=expression # binaryOperation // mind the operator precedence
           | left=expression operator=(PLUS|MINUS) right=expression        # binaryOperation
           | value=expression AS targetType=type                           # typeConversion
           | LPAREN expression RPAREN                                      # parenExpression
           | ID                                                            # varReference
           | MINUS expression                                              # minusExpression
           | INTLIT                                                        # intLiteral // "IntLiteral" is the name used in the generated Java code
           | DECLIT                                                        # decimalLiteral ;

type : INT     # integer
     | DECIMAL # decimal ;
