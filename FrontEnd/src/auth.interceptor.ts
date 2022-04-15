import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Router } from "@angular/router";
import { Observable, throwError } from "rxjs";
import { UserAuthService } from "./app/user-auth.service";
import { Injectable } from "@angular/core";


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private userAuthService: UserAuthService, private router: Router) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem("token")!;
        console.log(token);
        
        if (token !== null) {
            const req1= req.clone({
                headers: req.headers.set('Authorization',`Baerer ${token}`)
            });
            console.log(req1);
            return next.handle(req1);
        }
        return next.handle(req);
    }



}