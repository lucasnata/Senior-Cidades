package br.com.lucas.cidades.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.InputStream;
import java.util.List;

public class CsvUtil {

    public static <T> List<T> loadObjectList(Class<T> type, InputStream inputStream) throws Exception {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        MappingIterator<T> readValues =
                mapper.readerFor(type).with(bootstrapSchema).readValues(inputStream);
        return readValues.readAll();
    }
}
