/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _CALC_H_RPCGEN
#define _CALC_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct suma_1_argument {
	float arg1;
	float arg2;
};
typedef struct suma_1_argument suma_1_argument;

struct resta_1_argument {
	float arg1;
	float arg2;
};
typedef struct resta_1_argument resta_1_argument;

struct mult_1_argument {
	float arg1;
	float arg2;
};
typedef struct mult_1_argument mult_1_argument;

struct div_1_argument {
	float arg1;
	float arg2;
};
typedef struct div_1_argument div_1_argument;

#define CALCULADORA 0x20000155
#define CALCULADORAVER 1

#if defined(__STDC__) || defined(__cplusplus)
#define SUMA 1
extern  float * suma_1(float , float , CLIENT *);
extern  float * suma_1_svc(float , float , struct svc_req *);
#define RESTA 2
extern  float * resta_1(float , float , CLIENT *);
extern  float * resta_1_svc(float , float , struct svc_req *);
#define MULT 3
extern  float * mult_1(float , float , CLIENT *);
extern  float * mult_1_svc(float , float , struct svc_req *);
#define DIV 4
extern  float * div_1(float , float , CLIENT *);
extern  float * div_1_svc(float , float , struct svc_req *);
extern int calculadora_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define SUMA 1
extern  float * suma_1();
extern  float * suma_1_svc();
#define RESTA 2
extern  float * resta_1();
extern  float * resta_1_svc();
#define MULT 3
extern  float * mult_1();
extern  float * mult_1_svc();
#define DIV 4
extern  float * div_1();
extern  float * div_1_svc();
extern int calculadora_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_suma_1_argument (XDR *, suma_1_argument*);
extern  bool_t xdr_resta_1_argument (XDR *, resta_1_argument*);
extern  bool_t xdr_mult_1_argument (XDR *, mult_1_argument*);
extern  bool_t xdr_div_1_argument (XDR *, div_1_argument*);

#else /* K&R C */
extern bool_t xdr_suma_1_argument ();
extern bool_t xdr_resta_1_argument ();
extern bool_t xdr_mult_1_argument ();
extern bool_t xdr_div_1_argument ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_CALC_H_RPCGEN */
