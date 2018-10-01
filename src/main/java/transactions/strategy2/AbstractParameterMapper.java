package transactions.strategy2;

import java.sql.PreparedStatement;

abstract class AbstractParameterMapper<T> implements ParameterMapper<T> {

    protected PreparedStatement preparedStatement;

    AbstractParameterMapper(PreparedStatement ps) {
        preparedStatement = ps;
    }

}
