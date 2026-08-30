import { useActionState, useEffect, useRef, useState } from "react";
import './AddTransaction.css';
import txnFormAction from "./txnFormAction";

export interface TransactionFormState {
    status?: 'IDLE' | 'SUCCESS' | 'FAILED' | 'INVALID';
    message?: string;
}

function AddTransaction() {
    const [formState, formAction, isPending] = useActionState<TransactionFormState, FormData>(txnFormAction, { status: 'IDLE', message: '' });
    const [displayMessage, setDisplayMessage] = useState<string | undefined>(undefined);

    const handleInputChangeToClearMessage = () => {
       if (displayMessage) {
            setDisplayMessage(undefined);
        }
    }

    useEffect(() => {

        setDisplayMessage(formState.message);
        if (formState.status !== 'IDLE') {
            const timer = setTimeout(() => {
                setDisplayMessage(undefined);
            }, 5000);

            return () => clearTimeout(timer);
        }

    }, [formState]);

    return (
        <>
            <form
                action={formAction}
                onInput={handleInputChangeToClearMessage}
                className="add-transaction-container" 
            >
                {displayMessage&& <p className="error-text">{displayMessage}</p>}
                <div className="transaction-input-container">
                    <label htmlFor="amount">Amount</label>

                    <input
                        id="amount"
                        name="amount"
                        type="number"
                        step="0.01"
                        placeholder="Enter the amount"
                        required
                    />
                </div>

                <div className="transaction-input-container">
                    <label htmlFor="description">Short Description</label>
                    <input
                        id="description"
                        name="description"
                        type="text"
                        placeholder="Enter the description"
                    />
                </div>

                <button type="submit" disabled={isPending}>
                    {isPending ? "Adding..." : "Add Transaction"}
                </button>
            </form>
        </>
    );
}

export default AddTransaction;