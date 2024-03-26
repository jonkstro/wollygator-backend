package br.com.wollygator.main.services.utils;

import java.util.Calendar;
import java.util.Date;

public class CarteiraServiceUtil {
    public static Date getDataValidade(Date dataExpedicao) {
        // Crie um objeto Calendar a partir da data atual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataExpedicao);

        // Adicione 1 ano ao Calendar
        calendar.add(Calendar.YEAR, 1);

        return calendar.getTime();
    }
}
