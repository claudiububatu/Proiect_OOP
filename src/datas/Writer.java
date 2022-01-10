package datas;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public final class Writer {
    /**
     * The file where the data will be written
     */
    private final FileWriter file;

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * function built for writing the output
     */
    public void writeF(final AnnualChildrenPadre annualChildren) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().
                writeValueAsString(annualChildren);
        BufferedWriter writer = new BufferedWriter(this.file);
        writer.write(json);

        writer.close();
    }
}
