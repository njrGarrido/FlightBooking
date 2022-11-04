import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Title from './Title';
import Typography from "@material-ui/core/Typography";

const useStyles = makeStyles((theme) => ({
    seeMore: {
        marginTop: theme.spacing(3),
    },
}));

export default function Orders({firstName, lastName, age, id, nationality, flights}) {
    const classes = useStyles();
    return (
        <React.Fragment>
            <Title>
                <Typography component="h6" variant="subtitle1" color="blue" gutterBottom>
                   ID: {id}&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
                </Typography>
                <Typography component="h6" variant="subtitle1" color="blue" gutterBottom>
                   First Name: {firstName}&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
                </Typography>
                <Typography component="h6" variant="subtitle1" color="blue" gutterBottom>
                   Last Name: {lastName}&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
                </Typography>
                <Typography component="h6" variant="subtitle1" color="blue" gutterBottom>
                   Age: {age}&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
                </Typography>
                <Typography component="h6" variant="subtitle1" color="blue" gutterBottom>
                   Nationality: {nationality}&nbsp;&nbsp;&nbsp;
                </Typography>
                <Typography component="h6" variant="subtitle1" color="blue" gutterBottom>
                   Flights: {flights}&nbsp;&nbsp;&nbsp;
                </Typography>
            </Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Origin</TableCell>
                        <TableCell>Destination</TableCell>
                        <TableCell>Departure</TableCell>
                        <TableCell>Price</TableCell>

                    </TableRow>
                </TableHead>
                <TableBody>
                    { flights.map((i, n) => (
                        
                        <TableRow key={n}>
                            <TableCell>{i.id}</TableCell>
                            <TableCell>{i.origin}</TableCell>
                            <TableCell>{i.destination}</TableCell>
                            <TableCell>{i.departure}</TableCell>
                            <TableCell>{i.price}</TableCell>

                        </TableRow> 
                    ))}
                </TableBody>
            </Table>
            <div className={classes.seeMore}>
                <hr/>
            </div>
        </React.Fragment>
    );
}