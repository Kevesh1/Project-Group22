package dataaccess.mongodb.dto.transactions;

import budgetapp.model.transactions.Transaction;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * @author Johannes
 */
@BsonDiscriminator
public class TransactionDto {

    private ObjectId _id;

    private ObjectId budgetMonth;

    @BsonProperty
    private Transaction transaction;

    public TransactionDto() {
    }
}
