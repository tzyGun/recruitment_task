import React from 'react'
import { useEffect , useState, useCallback} from 'react';
import { checkProduct, handlerApiError } from '../../api/call-api';
import { debounce } from '../../utils/debounce';
import { DEBOUNCE_TIME_MS } from '../../enum/application.enum';
export const Counter = ({ item, onCounterChange }) => {
    const [counter, updateCounter] = useState(0);

    const isProductblocked = () => item?.isBlocked

    useEffect(() => {
        updateCounter(item.min)
        return () => {
            
        }
    }, [])


    useEffect(() => {
        verify(counter)
        return () => {
            
        }
    }, [counter])

    const verify = useCallback(
        debounce(counter => checkProduct({
            pid: item.pid,
            quantity: counter
        })
        .then(response=> {
            onCounterChange(item)})
        .catch(err=> handlerApiError(err)), DEBOUNCE_TIME_MS),
        []
      );

    const isRemoveButtonDisabled = () => counter == item.min

    const  incrementCounter = () => updateCounter(counter + 1)
    const  decrementCounter = () => updateCounter(counter < 0 ? 0 : counter - 1)

    return (
        <div>
            <button disabled={isProductblocked() || isRemoveButtonDisabled()} onClick={() => decrementCounter()}>-</button>
            <button disabled={isProductblocked()} onClick={() => incrementCounter()}>+</button>
            <span>Obecnie masz {counter} sztuk produktu: </span>
        </div>
    );
}



