import './summary.css'
import React from 'react'
export const Summary = ({ summary }) => {
    return (
        <div className="summary">
            <p>Podsumowanie: {summary} </p>
        </div>
    )
}
