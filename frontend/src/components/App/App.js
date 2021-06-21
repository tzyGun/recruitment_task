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
  const [quote, setQuote] = useState(0.0)



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

  const calculateQuote = (array) => {
    const sum = array.reduce(function (previousValue, currentValue, index, array) {
      return previousValue + (currentValue.price * currentValue.quantity)
    }, 0);
    setQuote(sum)
    console.log(sum)
  }
  const fillSummaryArray = (array, item) => {
    if (array.find((el) => el.pid === item.pid)) {
      const index = array.findIndex((el) => el.pid === item.pid)
      array[index] = item
      calculateQuote(array)
      return [...array]
    } else {
      return [...array, item]
    }
  }
  const viewContent = () => appState.isDataLoaded ?
    cart.map((item, index) => <ProductItem key={index} item={item} onCounterChange={(item) => setSummary((prev) => fillSummaryArray(prev, item))} />) : <Loader />

  const calculateSummary = () => { }
  return (
    <div className="container">
      <h3>Lista produktów</h3>
      <ul>
        {viewContent()}
      </ul>
      <Summary summary={quote} />
    </div>
  );
};

export {
  App
};
