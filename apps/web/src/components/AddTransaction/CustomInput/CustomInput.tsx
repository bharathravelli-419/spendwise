
interface CustomInputType {
    id :string;
    name:string;
    type:string;
    step:number | undefined;
    placeHolder:string;
    defaultValue:string |undefined;
    required: boolean
}

const CustomInput= ({id ,name ,type ,step, placeHolder, defaultValue, required} : CustomInputType)=> {
    return (
        <>
        <div className="transaction-input-container">
            <label htmlFor={name}>Amount</label>

            <input
                id={id}
                name={name}
                type={type}
                step={step}
                placeholder={placeHolder}
                defaultValue={defaultValue}
                required={required}
            />
        </div>
        </>
    );
};

export default CustomInput;