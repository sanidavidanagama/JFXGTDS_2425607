module edu.iit.erp.jfxgtds_2425607 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens edu.iit.erp.jfxgtds_2425607.app to javafx.fxml;
    opens edu.iit.erp.jfxgtds_2425607.controllers to javafx.fxml;
    opens edu.iit.erp.jfxgtds_2425607.model to javafx.base;

    exports edu.iit.erp.jfxgtds_2425607.app;
    exports edu.iit.erp.jfxgtds_2425607.controllers;
    exports edu.iit.erp.jfxgtds_2425607.model;
}
