import React, {useRef} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Button from "@material-ui/core/Button";
import axios from "axios";


function getModalStyle() {
    const top = 50;
    const left = 50;

    return {
        top: `${top}%`,
        left: `${left}%`,
        transform: `translate(-${top}%, -${left}%)`,
    };
}

const useStyles = makeStyles((theme) => ({
    paper: {
        position: 'absolute',
        width: 400,
        backgroundColor: theme.palette.background.paper,
        border: '2px solid #000',
        boxShadow: theme.shadows[5],
        padding: theme.spacing(2, 4, 3),
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column"
    },
    openModal: {
        backgroundColor: "green",
        color: "white",
        marginRight: 20
    },
    input: {
        padding: 10,
        margin: 10,
        border: '2px solid #000',
        borderColor: "green"
    }
}));

export default function SimpleModal({color, caption, data}) {
    const classes = useStyles();
    // getModalStyle is not a pure function, we roll the style only on the first render
    const [modalStyle] = React.useState(getModalStyle);
    const [open, setOpen] = React.useState(false);

    const origin = useRef(null);
    const destination = useRef(null);
    const customerID = useRef(null);

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const addFlight = () => {
        if ( origin.current.value !== "" && destination.current.value !== "") {
            axios.post(`/flights/customer/${customerID.current.value}`, {
                origin: origin.current.value,
                destination: destination.current.value 
            });

            window.location.reload();
        }
    };

    const body = (
        <div style={modalStyle} className={classes.paper}>

        <h3>Add Flight</h3>
            <h4>Origin</h4>
            <select className={classes.input} ref={origin} id="cars">
                <option value="Oporto">Oporto</option>
                <option value="Lisbon">Lisbon</option>
                <option value="Seville">Seville</option>
                <option value="Madrid">Madrid</option>
                <option value="Barcelona">Barcelona</option>
            </select>
            <h4>Destination</h4>
            <select className={classes.input} ref={destination} id="cars">
                <option value="Oporto">Oporto</option>
                <option value="Lisbon">Lisbon</option>
                <option value="Seville">Seville</option>
                <option value="Madrid">Madrid</option>
                <option value="Barcelona">Barcelona</option>
            </select>
            <h4>Departure</h4>
            <input className={classes.input} ref={customerID} type="date" min="0"/>
            <hr/>
            <Button variant="contained" color="primary" onClick={addFlight}>Add Flight</Button>
        </div>
    );

    return (
        <div>
            <Button className={classes.openModal} type="button" onClick={handleOpen}>
                Add Flight
            </Button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                {body}
            </Modal>
        </div>
    );
}
