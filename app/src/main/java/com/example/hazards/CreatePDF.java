package com.example.hazards;



import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreatePDF extends Activity {

    public static String DEST = ".pdf";
    public static final String TAG = AddProject.class.getSimpleName();
    public static String DEST2;
    private SendEmail mSendEmail = new SendEmail();





   /*   public void startPDF(final String[] mJobArray) {



           new Thread(new Runnable() {
                final String[] jobArray = mJobArray;


                @Override
                public void run() {
                    try {
                        createPdf(jobArray);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }   */








            public void createPdf(String[] emailJobArray, String DEST2) throws IOException, DocumentException {




                    File sdCard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdCard.getAbsolutePath() + "/HazardDocs");
                    String dir2 = sdCard.getAbsolutePath() + "/HazardDocs/";
                    Log.e(TAG, "filepath" + dir2 + "/");
                    dir.mkdirs();


                    //need to initialise storage, and select storage location for DEST

                    // Intent intent = getIntent();
                    // Bundle array = intent.getExtras();
                    // String[] jobArray = array.getStringArray("test");
                   //  String DEST2 = dir2 + emailJobArray[3] + DEST;


                    Log.e(TAG, "startPDF" + DEST2);

                    // File file = new File(DEST2);
                    // file.getParentFile().mkdirs();

                Log.e(TAG, "createPDF");
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(DEST2));
                document.open();
                // String text = emailJobArray[2];

                List list = new List(List.UNORDERED);
                int i;
                for (i = 0; i < emailJobArray.length; i++) {
                    ListItem item = new ListItem(emailJobArray[i]);
                    item.setAlignment(Element.ALIGN_JUSTIFIED);
                    list.add(item);

                }
                /* ListItem item = new ListItem(text);
                item.setAlignment(Element.ALIGN_JUSTIFIED);
                list.add(item);
                text = "a b c align ";
                for (int i = 0; i < 5; i++) {
                    text = text + text;
                }
                item = new ListItem(text);
                item.setAlignment(Element.ALIGN_JUSTIFIED);
                list.add(item);
                text = "supercalifragilisticexpialidocious ";
                for (int i = 0; i < 3; i++) {
                    text = text + text;
                }
                item = new ListItem(text);
                item.setAlignment(Element.ALIGN_JUSTIFIED);
                list.add(item); */
                document.add(list);
                document.close();
                Log.e(TAG, "createPdf 2" + emailJobArray[2]);
                  //  mSendEmail.Mail(DEST2, jobArray);

            }
        }

    /*
        Document document = new Document();



        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("HelloWorld.pdf"));

            document.open();
            document.add(new Paragraph("A Hello World PDF document."));
            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

} */






