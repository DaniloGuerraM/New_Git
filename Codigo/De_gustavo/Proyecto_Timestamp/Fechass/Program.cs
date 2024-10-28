using System;
using System.Globalization;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Collections.Generic;
public class Example1
{
    public static void Main()
    {
        /*
        string[] dateValues = { "30-12-2011", "12-30-2011","30-12-11", "12-30-11" };
        string pattern = "MM-dd-yy";
        DateTime parsedDate;

        foreach (var dateValue in dateValues)
        {
            if (DateTime.TryParseExact(dateValue, pattern, null, DateTimeStyles.None, out parsedDate)){
                Console.WriteLine("Converted '{0}' to {1:d}.",dateValue, parsedDate);
            }else{
                Console.WriteLine("Unable to convert '{0}' to a date and time.",dateValue);
            }
        }
        ////////////////////////////////////////////////////////////////////////
        Console.WriteLine("-------------------------------------------------");
        long fechar = 1728428813;

        DateTime date = DateTimeOffset.FromUnixTimeSeconds(fechar).DateTime;

        var horas = (DateTime.Now - date).ToString(@"dd\d\ hh\h\ mm\m\ ");
        Console.WriteLine(horas);
        ////////////////////////////////////////////////////////////////////////
        Console.WriteLine("-------------------------------------------------");
        long unix2 = 1724802345;
        long unix = new DateTimeOffset(DateTime.UtcNow).ToUnixTimeSeconds();


        DateTime date3 = DateTimeOffset.FromUnixTimeSeconds(unix).DateTime;
        DateTime date2 = DateTimeOffset.FromUnixTimeSeconds(unix2).DateTime;

        TimeSpan hora = date3.TimeOfDay;
        TimeSpan hora2 = date2.TimeOfDay;
        if(hora < hora2){
            Console.WriteLine("hora " + hora.ToString());
        }else{
            Console.WriteLine("hora2 " + hora2.ToString());
        }
        
        ////////////////////////////////////////////////////////////////////////
        Console.WriteLine("-------------------------------------------------");
        long num = new DateTimeOffset(DateTime.UtcNow).ToUnixTimeSeconds();
        long fechar = 1728428813;

        DateTime date = DateTimeOffset.FromUnixTimeSeconds(fechar).DateTime;
        DateTime dateNum = DateTimeOffset.FromUnixTimeSeconds(num).DateTime;

        var diaMes = dateNum.ToString("yyyy-MM-dd");
        var diaMes2 = date.ToString("yyyy-MM-dd");
        if (date == dateNum){
            Console.WriteLine("son iguales");
        }else if(date > dateNum){
           Console.WriteLine("HOY "+diaMes);
        }else{
        Console.WriteLine("AYER "+diaMes2);
        }
        */
        long num = new DateTimeOffset(DateTime.UtcNow).ToUnixTimeSeconds();
        DateTime utcDateTime = DateTimeOffset.FromUnixTimeSeconds(num).UtcDateTime;

        // Definir la zona horaria de Argentina
        TimeZoneInfo argentinaTimeZone = TimeZoneInfo.FindSystemTimeZoneById("Argentina Standard Time");

        // Convertir la hora UTC a la hora de Argentina
        DateTime argentinaTime = TimeZoneInfo.ConvertTimeFromUtc(utcDateTime, argentinaTimeZone);

        Console.WriteLine(argentinaTime);
    }
}
// The example displays the following output:
//    Unable to convert '30-12-2011' to a date and time.
//    Unable to convert '12-30-2011' to a date and time.
//    Unable to convert '30-12-11' to a date and time.
//    Converted '12-30-11' to 12/30/2011.
