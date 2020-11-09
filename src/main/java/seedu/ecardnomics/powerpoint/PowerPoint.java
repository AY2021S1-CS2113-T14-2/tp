package seedu.ecardnomics.powerpoint;

import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.beryx.awt.color.ColorFactory;
import seedu.ecardnomics.Ui;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.FlashCard;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PowerPoint {
    private Deck deck;
    private XMLSlideShow pptx = new XMLSlideShow();
    private XSLFSlideMaster defaultMaster = pptx.getSlideMasters().get(0);
    private String bgColorString;
    private String txtColorString;
    private Color bgColor;
    private Color txtColor;
    private ColorOption colorOpt;

    public static final String[][] COLOR_SCHEMES = {
            {"steelblue", "silver"},
            {"goldenrod", "crimson"},
            {"navy", "turquoise"},
            {"black", "orange"},
            {"black", "deeppink"},
            {"grey", "pink"},
            {"pink", "navy"},
            {"lightyellow", "seagreen"},
            {"maroon", "peachpuff"},
            {"azure", "darkgreen"}
    };

    public static final String PPTX_DIR = "pptx/";
    public static final String PPTX_EXT = ".pptx";

    /** Constructor for default printing. */
    public PowerPoint(Deck deck) {
        this.deck = deck;
        bgColorString = "white";
        txtColorString = "black";
        bgColor = ColorFactory.valueOf("white");
        txtColor = ColorFactory.valueOf("black");
        colorOpt = ColorOption.DEFAULT;
    }

    /** Constructor for -cs option. */
    public PowerPoint(Deck deck, int colorScheme) {
        this.deck = deck;
        bgColorString = COLOR_SCHEMES[colorScheme][0];
        txtColorString = COLOR_SCHEMES[colorScheme][1];
        bgColor = ColorFactory.valueOf(COLOR_SCHEMES[colorScheme][0]);
        txtColor = ColorFactory.valueOf(COLOR_SCHEMES[colorScheme][1]);
        colorOpt = ColorOption.COLOR_SCHEME;
    }

    /** Constructor for -oc option. */
    public PowerPoint(Deck deck, String bgColorString, String txtColorSting,
                      Color bgColor, Color txtColor) {
        this.deck = deck;
        this.bgColorString = bgColorString;
        this.txtColorString = txtColorSting;
        this.bgColor = bgColor;
        this.txtColor = txtColor;
        colorOpt = ColorOption.ORGINAL_COLOR;
    }

    private void newIntroSlide() {
        XSLFSlideLayout layout = defaultMaster.getLayout(SlideLayout.TITLE);
        XSLFSlide slide = pptx.createSlide(layout);

        slide.getBackground().setFillColor(bgColor);

        XSLFTextShape title = slide.getPlaceholder(0);
        XSLFTextShape subtitle = slide.getPlaceholder(1);
        slide.removeShape(subtitle);

        title.clearText();

        XSLFTextParagraph p = title.addNewTextParagraph();
        XSLFTextRun r = p.addNewTextRun();

        r.setText(deck.getName());
        r.setFontColor(txtColor);
        r.setFontSize(60.);
    }

    private void newSlide(String titleText, String text) {
        XSLFSlideLayout layout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
        XSLFSlide slide = pptx.createSlide(layout);

        XSLFTextShape title = slide.getPlaceholder(0);
        XSLFTextShape object = slide.getPlaceholder(1);

        title.clearText();
        object.clearText();

        XSLFTextParagraph p = title.addNewTextParagraph();
        XSLFTextRun r = p.addNewTextRun();

        r.setText(titleText);
        r.setFontColor(txtColor);
        r.setFontSize(50.);

        p = object.addNewTextParagraph();
        r = p.addNewTextRun();

        r.setText(text);
        r.setFontColor(txtColor);
        r.setFontSize(40.);
    }

    private void createPowerPoint() {
        newIntroSlide();

        for (int i = 0; i < deck.size(); ++i) {
            FlashCard card = deck.get(i);
            newSlide(Ui.QUESTION + (i + 1), card.getQuestion());
            newSlide(Ui.ANSWER, card.getAnswer());
        }
    }

    private void exportPowerPoint() {
        String fileName = PPTX_DIR + deck.getName() + PPTX_EXT;

        File folder = new File(PPTX_DIR);
        if (!folder.exists()) {
            folder.mkdir();
        }

        assert (folder.exists());

        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (Exception e) {
            Ui.printMessage(Ui.CREATE_NEW_FILE_ERROR_LINE + fileName);
        }

        assert (file.exists());

        try {
            FileOutputStream out = new FileOutputStream(fileName);
            try {
                pptx.write(out);
                out.close();
                pptx.close();
                Ui.printDeckPptxLine(deck.getName(), bgColorString, txtColorString, colorOpt);
            } catch (IOException e) {
                Ui.printMessage(Ui.WRITE_TO_FILE_ERROR_LINE);
            }
        } catch (FileNotFoundException e) {
            Ui.printMessage(fileName + Ui.FILE_NOT_FOUND_ERROR_LINE);
        }
    }

    /**
     * Creates a new PowerPoint Slide based on the current deck
     * and saves it as pptx/deckName.pptx
     */
    public void createNewPowerPoint() {
        createPowerPoint();
        exportPowerPoint();
    }
}
