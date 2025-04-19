import java.util.*;

/*
public class Main {
  public static void main(String[] args) {
    InvoiceFormat pdf = new PdfFormat();
    InvoiceFormat html = new HtmlFormat();

    InvoiceProcessor businessPdf = new BusinessInvoice(pdf);
    InvoiceProcessor individualHtml = new IndividualInvoice(html);

    businessPdf.generate(4200);
    individualHtml.generate(250);
  }
}
*/
public interface InvoiceFormat {
    public void export(String data);
}

public class PdfFormat implements InvoiceFormat {
  public void export(String data) {
    System.out.println("Exporting as PDF: " + data);
  }
}

public class HtmlFormat implements InvoiceFormat {
  public void export(String data) {
    System.out.println("Rendering as HTML: " + data);
  }
}

public abstract class InvoiceProcessor {
  protected InvoiceFormat format;

  public InvoiceProcessor(InvoiceFormat format) {
    this.format = format;
  }

  public abstract void generate(int amount);
}

public class BusinessInvoice extends InvoiceProcessor {
  public BusinessInvoice(InvoiceFormat format) {
    super(format);
  }

  public void generate(int amount) {
    String data = "[Business Invoice] Amount: " + amount + ", Includes VAT, Company Info, Signature";
    format.export(data);
  }
}

public class IndividualInvoice extends InvoiceProcessor {
  public IndividualInvoice(InvoiceFormat format) {
    super(format);
  }

  public void generate(int amount) {
    String data = "[Individual Invoice] Amount: " + amount + ", Personal Name, Standard Layout";
    format.export(data);
  }
}
