import apiClient from "./clients/apiClient";

const TransactionService = {

    create: async( transactionPayload: any)=>{
        await new Promise(resolve => setTimeout(resolve, 4000));
        const idemKey = crypto.randomUUID();
        const {data} = await apiClient.post('/transactions',transactionPayload,{
            headers:{
                'Idempotency-Key': idemKey,
            }
        });
        return data;
    }

}

export default TransactionService;