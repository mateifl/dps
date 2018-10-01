package transactions.strategy;

/**
 * This is the first implementation of the transaction using a Strategy.
 * Main Interface:
 * {@link transactions.strategy.Transaction}
 * This interface has an abstract implementation:
 * {@link transactions.strategy.AbstractJdbcTransaction}
 * which implements the common steps and leaves the specific step  to be implemented
 * in specific classes (ex. {@link transactions.basic.CustomerInsert})
 */