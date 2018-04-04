%%
%standalone
%class Lexer

%line
%column

%init{
yybegin(YYINITIAL);
%init}



Y			=		(y|Y)
O			=		(o|O)
PARA		=		(p|P)(a|A)(r|R)(a|A)
DESDE		=		(d|D)(e|E)(s|S)(d|D)(e|E)
HASTA		=		(h|H)(a|A)(s|S)(t|T)(a|A)
AVANCE		=		(a|A)(v|V)(a|A)(n|N)(c|C)(e|E)
MIENTRAS	=		(m|M)(i|I)(e|E)(n|N)(t|T)(r|R)(a|A)(s|S)
HACER		=		(h|H)(a|A)(c|C)(e|E)(r|R)
SI			=		(s|S)(i|I)
SINO		=		(s|S)(i|I)(n|N)(o|O)
DEVOLVER	=		(d|D)(e|E)(v|V)(o|O)(l|L)(v|V)(e|E)(r|R)
PROC		=		(p|P)(r|R)(o|O)(c|C)
CLASE		=		(c|C)(l|L)(a|A)(s|S)(e|E)
NUM			=		(n|N)(u|U)(m|M)
CAD			=		(c|C)(a|A)(d|D)
BOOL		=		(b|B)(o|O)(o|O)(l|L)
PUBLICO		=		(p|P)(u|U)(b|B)(l|L)(i|I)(c|C)(o|O)
PRIVADO		=		(p|P)(r|R)(i|I)(v|V)(a|A)(d|D)(o|O)
IMPRIMIR	=		(i|I)(m|M)(p|P)(r|R)(i|I)(m|M)(i|I)(r|R)
LEER		=		(l|L)(e|E)(e|E)(r|R)
FIN			= 		(f|F)(i|I)(n|N)

NUMERO 		=    	\-?[0-9]+(\.[0-9]+)?
IMPORTAR 	= 		(i|I)(m|M)(p|P)(o|O)(r|R)(t|T)(a|A)(r|R)
MOD 		= 		(m|M)(o|O)(d|D)
INTENTAR 	= 		(i|I)(n|N)(t|T)(e|E)(n|N)(t|T)(a|A)(r|R)
ATRAPAR 	= 		(a|A)(t|T)(r|R)(a|A)(p|P)(a|A)(r|R)
LANZAR 		= 		(l|L)(a|A)(n|N)(z|Z)(a|A)(r|R)
CONSTANTE 	= 		(c|C)(o|O)(n|N)(s|S)(t|T)(a|A)(n|N)(t|T)(e|E)
REVISAR 	= 		(r|R)(e|E)(v|V)(i|I)(s|S)(a|A)(r|R)
CASO 		= 		(c|C)(a|A)(s|S)(o|O)
COMO 		= 		(c|C)(o|O)(m|M)(o|O)
DEFECTO 	= 		(d|D)(e|E)(f|F)(e|E)(c|C)(t|T)(o|O)
CON 		= 		(c|C)(o|O)(n|N)
TERMINAR 	= 		(t|T)(e|E)(r|R)(m|M)(i|I)(n|N)(a|A)(r|R)
EXCEPTO 	= 		(e|E)(x|X)(c|C)(e|E)(p|P)(t|T)(o|O)
MOSTRAR 	= 		(m|M)(o|O)(s|S)(t|T)(r|R)(a|A)(r|R)
EJECUTAR 	= 		(e|E)(j|J)(e|E)(c|C)(u|U)(t|T)(a|A)(r|R)
EN 			= 		(e|E)(n|N)
CONTINUAR 	= 		(c|C)(o|O)(n|N)(t|T)(i|I)(n|N)(u|U)(a|A)(r|R)
FINALIZAR 	= 		(f|F)(i|I)(n|N)(a|A)(l|L)(i|I)(z|Z)(a|A)(r|R)
ES 			= 		(e|E)(s|S)
VAR 		= 		([a-z]|[A-Z])([a-z]|[A-Z]|_|[0-9])*

MEI			=		\<\=
MAI			=		\>\=
II			=		\=\=

FIN_LINEA	=		(\r|\n)
CARACTER	=		[^\r\n]
COMENTARIO	= {COMENTARIO_NORMAL} | {COMENTARIO_LINEA}
COMENTARIO_NORMAL   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
COMENTARIO_LINEA	= "//" {CARACTER}* {FIN_LINEA}?

DELIMITADOR = \[|\]|\+=|\-=|%=|>>=|<<=|\*=|&=|\{|\}|\(|\)|\/=|\|=|\*\*=|\/\/=|\^=
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

%%

{Y}
		{
			System.out.println(yytext() + " es el operador logico and");
		}
		
{O}
		{
			System.out.println(yytext() + " es el operador logico or");
		}
		
{PARA}
		{
			System.out.println(yytext() + " es el ciclo for");
		}
		
{DESDE}
		{
			System.out.println(yytext() + " es un acompañante para el ciclo for que inicializa la variable iteradora");
		}
		
{HASTA}
		{
			System.out.println(yytext() + " es un acompañante para el ciclo for que establece la condicion de salida");
		}
		
{AVANCE}
		{
			System.out.println(yytext() + " es un acompañante para el ciclo for que indica cuanto cambia el iterador");
		}
		
{MIENTRAS}
		{
			System.out.println(yytext() + " es el ciclo while");
		}
		
{HACER}
		{
			System.out.println(yytext() + " es el acompañante do del ciclo while");
		}
		
{SI}
		{
			System.out.println(yytext() + " es la condicional if");
		}
		
{SINO}
		{
			System.out.println(yytext() + " es el acompañante del if llamado else");
		}
		
{DEVOLVER}
		{
			System.out.println(yytext() + " es el return");
		}
		
{PROC}
		{
			System.out.println(yytext() + " es una funcion que no devuelve o void");
		}
		
{CLASE}
		{
			System.out.println(yytext() + " es una clase");
		}
		
{NUM}
		{
			System.out.println(yytext() + " es el tipo de dato generico numerico");
		}
		
{CAD}
		{
			System.out.println(yytext() + " es el tipo de dato generico para cadenas de caracteres");
		}
		
{BOOL}
		{
			System.out.println(yytext() + " es el tipo de dato booleano");
		}
		
{PUBLICO}
		{
			System.out.println(yytext() + " es seguridad publica");
		}
		
{PRIVADO}
		{
			System.out.println(yytext() + " es seguridad privada");
		}
		
{IMPRIMIR}
		{
			System.out.println(yytext() + " es para imprimir en consola");
		}
		
{LEER}
		{
			System.out.println(yytext() + " es para leer lo que ponga el usuario en consola");
		}

{FIN}
		{
			System.out.println("El programa va a terminar");
			return 0;
		}
		
"\*"
		{
			System.out.println(yytext() + " es el operador de multiplicacion");
		}
		
"<"
		{
			System.out.println(yytext() + " es el operador 'menor que'");
		}
		
"\^"
		{
			System.out.println(yytext() + " es el operador de potencia");
		}
		
"-"
		{
			System.out.println(yytext() + " es el operador de resta");
		}
		
">"
		{
			System.out.println(yytext() + " es el operador 'mayor que'");
		}
		
"="
		{
			System.out.println(yytext() + " es el operador de igualdad");
		}
		



{IMPORTAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }
            
{MOD}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{INTENTAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }            

{ATRAPAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{LANZAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{CONSTANTE}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }
            
{REVISAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{CASO}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }            

{COMO}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{DEFECTO}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{CON}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }
            
{TERMINAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{EXCEPTO}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }            

{MOSTRAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{EJECUTAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{EN}    {
            System.out.println(yytext() + " - palabra reservada");
        }
            
{CONTINUAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }

{FINALIZAR}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }            

{ES}    
		{
            System.out.println(yytext() + " - palabra reservada");
        }
        
{MEI}    
		{
            System.out.println(yytext() + " - operador menor o igual");
        }
        
{MAI}    
		{
            System.out.println(yytext() + " - operador mayor o igual");
        }
        
"!"    
		{
			System.out.println(yytext() + " - operador distinto");
		}

"\/"
		{
			System.out.println(yytext() + " - operador de division");
		}
		
"\+"
		{
			System.out.println(yytext() + " - operador de suma");
		}
        
{II}    
		{
            System.out.println(yytext() + " - operador de comparacion de igualdad");
        }
 
{NUMERO}    
		{
            System.out.println(yytext() + " - es un numero");
        }
 
{VAR}    
		{
            System.out.println(yytext() + " - nombre de variable");
        }
{COMENTARIO}
		{
			System.out.println(yytext() + " - comentario");
		}
{DELIMITADOR}
        {
            System.out.println(yytext() + " - delimitador");
        }

/* whitespace */
{WhiteSpace}
        { /* ignore */ }


[^]     { throw new Error("Illegal character <"
            + yytext()+">"); }