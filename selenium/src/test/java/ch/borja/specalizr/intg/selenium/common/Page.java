package ch.borja.specalizr.intg.selenium.common;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
@Builder
public class Page {
    private String bodyContent;
    private String scriptContent;
    @Builder.Default
    private String template = "html/testTemplate.html";

    @SneakyThrows
    private String build() {
        final var filePath = Page.class.getClassLoader().getResource(this.template);
        assert filePath != null;
        String templateContent = Files.readString(Path.of(filePath.toURI()));
        if (StringUtils.isNotBlank(this.scriptContent)) {
            templateContent = templateContent.replace("</script>", String.format("%s</script>", this.scriptContent));
        }
        if (StringUtils.isNotBlank(this.bodyContent)) {
            templateContent = templateContent.replace("<body>", String.format("<body>%s", this.bodyContent));
        }
        return templateContent;
    }

    @SneakyThrows
    public URI generate() {
        final Path tempFile = Files.createTempFile("index", ".html");
        Files.writeString(tempFile, this.build());
        return tempFile.toUri();
    }

    public String generateAsDataUrl() {
        return String.format("data:text/html,%s", this.build());
    }
}
