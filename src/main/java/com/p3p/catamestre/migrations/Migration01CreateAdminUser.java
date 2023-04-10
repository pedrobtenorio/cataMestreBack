package br.com.gehealthcare.bidding.migrations;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Migration01CreateAdminUser  implements CustomTaskChange  {
    @Override
    public void execute(Database database) throws CustomChangeException {
        try {
            var dbConn = (JdbcConnection) database.getConnection(); // autocommit is false
            var insertStatement = dbConn.createStatement();
            try {
                final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                var password = passwordEncoder.encode("admin123");
                var updateQuery = String.format("INSERT INTO users (name, email, password, role, created_date, last_updated_date)\n" +
                                "VALUES ('Admin', 'admin@example.com', '%s', 'ADMIN', NOW(), NOW());",
                        password
                );
                insertStatement.executeUpdate(updateQuery);

            } catch (Exception e) {
                throw new CustomChangeException(e);
            }
        } catch (CustomChangeException | DatabaseException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setUp() throws SetupException {

    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {

    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }
}
