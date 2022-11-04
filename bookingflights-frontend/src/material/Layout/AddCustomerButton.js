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
        color: "white"
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

    const firstName = useRef(null);
    const lastName = useRef(null);
    const nationality = useRef(null);
    const age = useRef(null);
    

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const addCustomer = () => {
        if (firstName.current.value !== "" && lastName.current.value !== "" && age.current.value !== "") {
            axios.post('customers', {
                firstName: firstName.current.value,
                lastName: lastName.current.value,
                age: age.current.value,
                nationality: nationality.current.value
            });

            window.location.reload();
        }
    };

    const body = (
        <div style={modalStyle} className={classes.paper}>
            <h3>Add Customer</h3>
            <input className={classes.input} ref={firstName} type="text" placeholder="Enter Fist Name"/>
            <input className={classes.input} ref={lastName} type="test" placeholder="Enter Last Name"/>
            <input className={classes.input} ref={nationality} type="text" placeholder="Enter Nationality"/>
            <input className={classes.input} ref={age} type="number" min="0" placeholder="Enter Age"/>
            
            <hr/>
            <Button variant="contained" color="primary" onClick={addCustomer} >Add Customer</Button>
        </div>
    );

    return (
        <div>
            <Button  variant="contained" className={classes.openModal} type="button" onClick={handleOpen}>
                Add Customer
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