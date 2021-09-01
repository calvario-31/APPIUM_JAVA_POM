package utilities;

import com.poiji.bind.Poiji;
import models.CredentialsModel;

import java.io.File;

public class DataReader {
    private final String EXCEL_PATH = "src/test/resources/data/testData.xlsx";

    public CredentialsModel getLockedOutCredentials(){
        return Poiji.fromExcel(new File(EXCEL_PATH), CredentialsModel.class).get(1);
    }
}
