import TransactionService from "../../api/transactionService";
import type { TransactionFormState } from "./AddTransaction";

const txnFormAction = async (prevState: TransactionFormState, formData: FormData): Promise<TransactionFormState> => {

    const amount = Number(formData.get("amount"));
    const description = formData.get("description");

    if (!amount || isNaN(amount) || amount <=0 ) {
        return {
            status: 'INVALID',
            message: 'INVALID AMOUNT'
        }
    }

    try {
        await TransactionService.create({
            accountId: "caf89087-d5fc-409f-b28b-968353cadf35",
            categoryId: "b0c37a96-4cae-4e4a-ab0a-26fbd42a2a78",
            amount: amount,
            currency: "INR",
            direction: "DEBIT",
            occurredAt: new Date().toISOString(),
            note: description,
        });
        return {
            status: 'SUCCESS',
            message: 'ADDED'
        }
    } catch (error) {
        return {
            status: 'FAILED',
            message: 'FAILED TO ADD'
        }
    }
}

export default txnFormAction;