import org.xhtmlrenderer.pdf.ITextRenderer;
import org.w3c.dom.Document;
import org.xhtmlrenderer.resource.XMLResource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileOutputStream;

public class Convertidor {
    public static void main(String[] args) {
        try {
            // Paso 1: Leer el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("ruta_del_archivo.xml");

            // Paso 2: Transformar XML a HTML
            // (Puedes implementar la lógica para convertir el documento XML a HTML aquí)

            // En este ejemplo, simplemente se carga una plantilla HTML desde el archivo resources/plantilla.html
            String htmlContent = Util.leerArchivo("plantilla.html");

            // Paso 3: Generar el archivo HTML
            String outputPath = "output/archivo.html";
            Util.guardarArchivo(outputPath, htmlContent);

            // Paso 4: Generar el archivo PDF desde el HTML
            generarPDF(outputPath, "output/archivo.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generarPDF(String inputPath, String outputPath) throws Exception {
        Document document = XMLResource.load(inputPath).getDocument();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(document, null);
        renderer.layout();
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            renderer.createPDF(fos);
        }
    }
}
