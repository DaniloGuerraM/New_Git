using System;
using System.Collections.Generic;
using CasculasFiguras;
// See https://aka.ms/new-console-template for more information


Aritmetica a = new Aritmetica();
Poligono p = a.CrearPoligono(new int[] {10,2});

Console.WriteLine("Area {0}", p.CalcularArea());
Console.WriteLine("perimetro {0}", p.CalcularPerimetro());
