import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Router } from "@angular/router";
import { Observable, throwError } from "rxjs";

import { Injectable } from "@angular/core";
import { AuthService } from "./app/auth.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private userAuthService: AuthService, private router: Router) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem("token")!;
        console.log("intercetto la richiesta");
        if (token !== null) {
            const req1= req.clone({
                headers: req.headers.set('Authorization',`Bearer ${token}`)
            });
            console.log(req1);
            return next.handle(req1);
        }
        return next.handle(req);
    }



}