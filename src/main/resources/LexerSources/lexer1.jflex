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

//Operadores compuestos

MEI			=		\<\=
MAI			=		\>\=
II			=		\=\=

//Otras declaraciones

NUMERO 		=    	[0-9]+(\.[0-9]+)?
VAR 		= 		([a-z]|[A-Z])([a-z]|[A-Z]|_|[0-9])*

FIN_LINEA	=		(\r|\n)
CARACTER	=		[^\r\n]
COMENTARIO	= {COMENTARIO_NORMAL} | {COMENTARIO_LINEA}
COMENTARIO_NORMAL   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
COMENTARIO_LINEA	= \/\/{CARACTER}*{FIN_LINEA}?

ESPACIO     =       {FIN_LINEA} | [ \t\f]

CADENA      =       \"(([^\"][^\"]|\\\")*([^\"][^\\\"]|[^\\\"]|\\\"))?\"

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


"-"
		{
			return symbol(sym.RESTA);
		}

"="
		{
			return symbol(sym.ASIGNACION);
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
          return symbol(sym.MENORQUE);
    }

">"
    {
        return symbol(sym.MAYORQUE);
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



{COMENTARIO}
	{
	    /* no haceer nada*/
	}


"["
    {
        return symbol(sym.CORCHETEABIERTO);
    }

"]"
     {
            return symbol(sym.CORCHETECERRADO);
     }

"{"
     {
            return symbol(sym.LLAVEABIERTO);
     }

"}"
     {
            return symbol(sym.LLAVECERRADO);
     }

"("
       {
              return symbol(sym.PARENTESISABIERTO);
       }


")"
       {
              return symbol(sym.PARENTESISCERRADO);
       }

":"
    {
    return symbol(sym.DOSPUNTOS);
    }

","
    {
    return symbol(sym.COMA);
    }


{ESPACIO}
        {
            /* no hacer nada */
        }

{VERDADERO}
        {
           return symbol(sym.VERDADERO);
        }

{FALSO}
        {
            return symbol(sym.FALSO);
        }

{CADENA}
        {
           return symbol(sym.CADENA,new String(yytext()));
        }

{NUMERO}
		{
           return symbol(sym.NUMERO, new Double(yytext()));

        }

{VAR}
		{
           return symbol(sym.VAR, new String(yytext()));
        }

 <<EOF>>
        {
            return symbol(sym.EOF);
        }


[^]     { throw new Error("Illegal character <" + yytext()+">"); }
