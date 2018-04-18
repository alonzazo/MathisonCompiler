/*
Mathison

Instrucciones para correr el analizador léxico:
1. Compilar el archivo lexer1.jflex con Jflex
2. Se generará el archivo Lexer.java y se corre con algún IDE con soporte para Java
3. Se ingresan las palabras, cadenas, numeros o símbolos por medio de línea de
comando o en un archivo de texto
4. Las reglas son las siguientes:
	- Las variables deben empezar con una letra y pueden tener solamente letras,
	números y guiones bajos
	- Los números pueden ser negativos o positivos y pueden tener o no decimales
	separados por un punto
	- Las palabras reservadas se pueden escribir en mayúsculas, minúsculas o
	con un combinación de ambas, estas se listan más abajo
	- Los comentarios se escriben con dos slash, para comentarios de una
	línea o con slash-asterisco asterisco-slash, para comentarios multilínea
	y ambos aceptan cualquier caracter
	- Los literales de cadena se escriben entre comillas y aceptan cualquier
	carecter que no sean comillas a menos que esten precedidas por un backslash
	- Se pueden escribir expresiones separadas o no por espacios que serán
	identificadas por el analizador, esto no aplica para comentarios o literales
	de cadena donde identifica toda la expresión como una sola
*/

%%
%standalone
%class Lexer

%line
%column

%cup

/*
    Declaraciones

    El codigo entre %{  y %} sera copiado integramente en el
    analizador generado.
*/
%{
    /*  Generamos un java_cup.Symbol para guardar el tipo de token
        encontrado */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    /* Generamos un Symbol para el tipo de token encontrado
       junto con su valor */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

%init{
yybegin(YYINITIAL);
%init}

//Palabras reservadas

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
REC			=		(r|R)(e|E)(c|C)
CAD			=		(c|C)(a|A)(d|D)
BOOL		=		(b|B)(o|O)(o|O)(l|L)
PUBLICO		=		(p|P)(u|U)(b|B)(l|L)(i|I)(c|C)(o|O)
PRIVADO		=		(p|P)(r|R)(i|I)(v|V)(a|A)(d|D)(o|O)
IMPRIMIR	=		(i|I)(m|M)(p|P)(r|R)(i|I)(m|M)(i|I)(r|R)
LEER		=		(l|L)(e|E)(e|E)(r|R)
FIN		= 		(f|F)(i|I)(n|N)
VERDADERO	=		(V|v)(E|e)(R|r)(D|d)(A|a)(D|d)(E|e)(R|r)(O|o)
FALSO		=		(F|f)(A|a)(L|l)(S|s)(O|o)
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
FINALMENTE 	= 		(F|f)(I|i)(N|n)(A|a)(L|l)(M|m)(E|e)(N|n)(T|t)(E|e)

//Operadores compuestos

MEI			=		\<\=
MAI			=		\>\=
II			=		\=\=

//Otras declaraciones

NUMERO 		=    		\-?[0-9]+(\.[0-9]+)?
VAR 		= 		([a-z]|[A-Z])([a-z]|[A-Z]|_|[0-9])*

FIN_LINEA	=		(\r|\n)
CARACTER	=		[^\r\n]
COMENTARIO	= {COMENTARIO_NORMAL} | {COMENTARIO_LINEA}
COMENTARIO_NORMAL   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
COMENTARIO_LINEA	= \/\/{CARACTER}*{FIN_LINEA}?

ESPACIO     =       {FIN_LINEA} | [ \t\f]

CADENA      =       \"(([^\"][^\"]|\\\")*([^\"][^\\\"]|[^\\\"]|\\\"))?\"
DELIMITADOR = \[|\]|\+=|\-=|%=|>>=|<<=|\*=|&=|\{|\}|\(|\)|\/=|\|=|\*\*=|\/\/=|\^=|:|::

%%

{Y}
		{
			return symbol(sym.Y);
		}

{O}
		{
			return symbol(sym.O);
		}

	/*Lo que se utiliza para los ciclos, inicia con CI*/

{PARA}
		{
			return symbol(sym.PARA);
		}

{DESDE}
		{
			return symbol(sym.DESDE);
		}

{HASTA}
		{
			return symbol(sym.HASTA);
		}

{AVANCE}
		{
			return symbol(sym.AVANCE);
		}

{MIENTRAS}
		{
			return symbol(sym.MIENTRAS);
		}

{HACER}
		{
			return symbol(sym.HACER);
		}

{SI}
		{
			return symbol(sym.SI);
		}

{SINO}
		{
			return symbol(sym.SINO);
		}

{DEVOLVER}
		{
			return symbol(sym.DEVOLVER);
		}

		/* PALABRAS RESERVADAS, PR */

{PROC}
		{
			return symbol(sym.PROC);
		}

{CLASE}
		{
			return symbol(sym.CLASE);
		}

{NUM}
		{
			return symbol(sym.NUM);
		}

{REC}
		{
			return symbol(sym.REC);
		}

{CAD}
		{
			return symbol(sym.CAD);
		}

{BOOL}
		{
			return symbol(sym.BOOL);
		}

{PUBLICO}
		{
			return symbol(sym.PUBLICO);
		}

{PRIVADO}
		{
			return symbol(sym.PRIVADO);

		}

{IMPRIMIR}
		{
			return symbol(sym.IMPRIMIR);
		}

{LEER}
		{
			return symbol(sym.LEER);
		}

{FIN}
		{
            return symbol(sym.FIN);
		}

		/*Operadores numericos ON*/

"\*"
		{
			return symbol(sym.MULTIPLICACION);
		}

"\^"
		{
			return symbol(sym.POTENCIA);
		}

"-"
		{
			return symbol(sym.RESTA);
		}

"="
		{
			return symbol(sym.IGUAL);
		}

{MOD}
        	{
			return symbol(sym.MOD);
             }

"\/"
   {
      return symbol(sym.DIVISION);
    }

 "\+"
     {
       return symbol(sym.SUMA);
       }

             		/*OPERADORES DE COMPARACION OC*/

"<"
    {
          return symbol(sym.OC_MENORQUE);
    }

">"
    {
        return symbol(sym.OC_MAYORQUE);
    }

{MEI}
    {
        return symbol(sym.MENOROIGUAL);
    }

{MAI}
    {
        return symbol(sym.MAYOROIGUAL);
    }

"!"
    {
        return symbol(sym.DISTINTO);
    }

{II}
    {
	    return symbol(sym.IGUAL);
    }



/*PALABRAS RESERVADAS MENOS UTILIZADAS PR*/

{IMPORTAR}
		{
			return symbol(sym.IMPORTAR);
        }


{INTENTAR}
		{
			return symbol(sym.INTENTAR);
        }

{ATRAPAR}
		{
			return symbol(sym.ATRAPAR);
        }

{LANZAR}
		{
			return symbol(sym.LANZAR);
        }

{CONSTANTE}
		{
			return symbol(sym.CONSTANTE);
        }

{REVISAR}
		{
			return symbol(sym.REVISAR);
        }

{CASO}
		{
			return symbol(sym.CASO);
        }

{COMO}
		{
			return symbol(sym.COMO);
        }

{DEFECTO}
		{
			return symbol(sym.DEFECTO);
        }

{CON}
		{
			return symbol(sym.CON);
        }

{TERMINAR}
		{
			return symbol(sym.TERMINAR);
        }

{EXCEPTO}
		{
			return symbol(sym.EXCEPTO);
        }

{MOSTRAR}
		{
			return symbol(sym.MOSTRAR);
        }

{EJECUTAR}
		{
			return symbol(sym.EJECUTAR;
        }

{EN}    {
			return symbol(sym.EN);
        }

{CONTINUAR}
		{
			return symbol(sym.CONTINUAR);
        }

{FINALMENTE}
		{
			return symbol(sym.FINALMENTE);
        }

{NUMERO}
		{
           return symbol(sym.CADENA, new Double(yytext()));

        }

{VAR}
		{
           return symbol(sym.VAR, new String(yytext()));
        }

{COMENTARIO}
	{
	    /* no haceer nada*/
	}

{DELIMITADOR}
        {
            return symbol(sym.DELIMITADOR,new String(yytext()));
        }

{ESPACIO}
        {
            /* no hacer nada */
        }

{CADENA}
        {
           return symbol(sym.CADENA,new String(yytext()));
        }


{VERDADERO}
        {
           return symbol(sym.VERDADERO);
        }
{FALSO}
        {
            return symbol(sym.FALSO);
        }


[^]     { throw new Error("Illegal character <"
            + yytext()+">"); }