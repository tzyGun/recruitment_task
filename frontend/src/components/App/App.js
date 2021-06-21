import React from 'react';
import './App.css';
import { getCart } from '../../api/call-api';
import { useEffect, useState } from 'react';
import { ProductItem } from '../product/product-item';
import { Loader } from '../loader/loader';
import { Summary } from '../summary/summary';

const App = () => {
  const [appState, setAppState] = useState({ isDataLoaded: false })
  const [cart, setCart] = useState([])
  const [summary, setSummary] = useState([])



  useEffect(async () => {
    const data = await getCart()
    setCart((cart) => [...cart, ...data])
    setAppState({ isDataLoaded: true })
    return () => {
      setCart([])
    }
  }, [])

  useEffect(() => {
    console.log(summary)
    return () => {
    }
  }, [summary])

  const viewContent = () => appState.isDataLoaded ? 
  cart.map((item, index) => <ProductItem key={index} item={item} onCounterChange={(item) => setSummary((prev)=> [...prev, item])}/>) : <Loader />

  const calculateSummary = ()=>{}
  return (
    <div className="container">
      <h3>Lista produktów</h3>
      <ul>
        {viewContent()}
      </ul>
      <Summary summary={calculateSummary()}/>
    </div>
  );
};

export {
  App
};
