import { useActionState, useEffect, useState } from "react";
import './AddTransaction.css';
import txnFormAction from "./txnFormAction";
import CustomInput from "./CustomInput/CustomInput";

export interface TransactionFormState {
    status?: 'IDLE' | 'SUCCESS' | 'FAILED' | 'INVALID';
    message?: string;
    enteredFields?:{
        amount?:string;
        description?:string;
    }
}

function AddTransaction() {
    const [formState, formAction, isPending] = useActionState<TransactionFormState, FormData>(txnFormAction, { status: 'IDLE', message: '', enteredFields: {} });
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
                <CustomInput 
                id="amount"
                name="amount"
                type="number"
                step={0.01}
                placeHolder="Enter the amount"
                defaultValue={formState?.enteredFields?.amount}
                required={true}
                />
                <CustomInput 
                id="description"
                name="description"
                type="text"
                step={undefined}
                placeHolder="Enter the description"
                defaultValue={formState?.enteredFields?.description}
                required={false}
                />

                <button type="submit" disabled={isPending}>
                    {isPending ? "Adding..." : "Add Transaction"}
                </button>
            </form>
        </>
    );
}

export default AddTransaction;