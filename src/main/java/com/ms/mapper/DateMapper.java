package com.ms.mapper;

import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime asOffSetDateTime(Timestamp ts){

        if(ts!=null){
            return OffsetDateTime.of(ts.toLocalDateTime().getYear(),ts.toLocalDateTime().getMonthValue()
                    ,ts.toLocalDateTime().getDayOfMonth(),ts.toLocalDateTime().getHour(),
                    ts.toLocalDateTime().getMinute(),ts.toLocalDateTime().getSecond(),ts.toLocalDateTime().getNano()
                    , ZoneOffset.UTC);

        }else{
            return null;
        }
    }

    public Timestamp asTimeStamp(OffsetDateTime ofs){

        if(ofs!=null){
            return Timestamp.valueOf(ofs.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }else{
            return null;
        }
    }
}
