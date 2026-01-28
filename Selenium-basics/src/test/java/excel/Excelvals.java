
package excel;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelvals {

    /** Relative path to the Excel file (no hard-coded user-specific paths) */
    private static final String RELATIVE_EXCEL_PATH =
            Paths.get("src", "test", "resources", "excel", "dob-data.xlsx").toString();

    /** Target sheet name */
    private static final String SHEET_NAME = "SignUpData";


    public static String getCell(int rowIndex, int colIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(RELATIVE_EXCEL_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + SHEET_NAME);
            }

            Row row = sheet.getRow(rowIndex);
            if (row == null) return "";

            Cell cell = row.getCell(colIndex);
            if (cell == null) return "";

            return cell.toString().trim();
        }
    }


    // ----------------- First data row getters (Row 1) -----------------

    public static String getFirstName()      throws IOException { return getCell(1, 0); }
    public static String getSurname()        throws IOException { return getCell(1, 1); }
    public static String getMobileNumber()   throws IOException { return getCell(1, 2); }
    public static String getGender()         throws IOException { return getCell(1, 3); }
    public static String getDayVisibleText() throws IOException { return getCell(1, 4); }
    public static String getMonthIndex()     throws IOException { return getCell(1, 5); }
    public static String getYearValue()      throws IOException { return getCell(1, 6); }

}

