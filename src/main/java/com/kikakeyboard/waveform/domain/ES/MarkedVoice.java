package com.kikakeyboard.waveform.domain.ES;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kikakeyboard.waveform.domain.Property;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

/**
 * @Author 毛伟
 * @Date 9/18/17  09:56
 */

@Data
@Document(indexName = "marked_voice", type = "voice")
public class MarkedVoice {
    @Id
    @JsonIgnore
    private String id;
    private Property property;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private LocalDate createTime;
}
