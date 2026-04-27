import { useEffect, useState } from "react";

function App(){
  const [state, setState] = useState('');
  useEffect(()=>{
    fetch('/api/v1/health/ping')
    .then(async res => {
      if(res.ok){
       return await res.json();
      }
    })
    .then(data =>{
      setState(data);
    })
  },[])
  return (
    <>
    {JSON.stringify(state)}
    </>
  );
}
export default App;