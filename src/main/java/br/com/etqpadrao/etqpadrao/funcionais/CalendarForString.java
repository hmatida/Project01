package br.com.etqpadrao.etqpadrao.funcionais;

import java.util.Calendar;

public class CalendarForString {
    public String transformForQuery (Calendar data){
        String day=Integer.toString(data.get(Calendar.DAY_OF_MONTH));
        String month=Integer.toString(data.get(Calendar.MONTH)+1);
        String year=Integer.toString(data.get(Calendar.YEAR));
        if (day.length()!=2){
            day="0"+day;
        }
        if (month.length()!=2){
            month="0"+month;
        }
        return year+"-"+month+"-"+day;
    }
}
