package pt.raphaelneves.bao.test.codingchallenge.integrations.importers;

import pt.raphaelneves.bao.test.codingchallenge.integrations.dtos.DataWrapper;

public interface DataImporter {
    void importData();
    <T extends DataWrapper> T wrapResponse(String response);
}
