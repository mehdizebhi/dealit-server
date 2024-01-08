package ir.dealit.restful.util.constant;

public class HTMLEmailTemplate {

    private static String template = """
            <html dir="rtl">
              <head>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                <link href="https://cdn.jsdelivr.net/gh/rastikerdar/vazirmatn@v33.003/Vazirmatn-font-face.css" rel="stylesheet" type="text/css"/>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
                <style>
                    body {
                        font-family: Vazirmatn, sans-serif !important;
                      }
                </style>
              </head>
              <body class="bg-light">
                <div class="container">
                  <div class="card my-10">
                    <div class="card-body">
                      <h1 class="h3 mb-2">دیلیت | Dealit</h1>
                      <h5 class="text-teal-700">%s</h5>
                      <hr>
                      <div class="space-y-3">
                        <p class="text-gray-700">%s</p>
                      </div>
                      <hr>
                      <a class="btn btn-primary" href="%s" target="_blank">%s</a>
                    </div>
                  </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
              </body>
            </html>  
            """;

    private static String confirmation = """
            <html dir="rtl">
              <head>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                <link href="https://cdn.jsdelivr.net/gh/rastikerdar/vazirmatn@v33.003/Vazirmatn-font-face.css" rel="stylesheet" type="text/css"/>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
                <style>
                    body {
                        font-family: Vazirmatn, sans-serif !important;
                      }
                </style>
              </head>
              <body class="bg-light">
                <div class="container">
                  <div class="card my-10">
                    <div class="card-body">
                      <h1 class="h3 mb-2">دیلیت | پلتفرم اشتغال محور</h1>
                      <h5 class="text-teal-700">تایید ایمیل حساب کاربری</h5>
                      <hr>
                      <div class="space-y-3">
                        <p class="text-gray-700">کد تایید ایمیل: %s</p>
                        <p class="text-gray-700">
                          درصورتی که شما اقدامی برای دریافت این ایمیل انجام ندادید یا نمی دانید چرا این ایمیل را دریافت کردید لطفا این ایمیل را نادیده بگیرید.
                        </p>
                      </div>
                      <hr>
                      <a class="btn btn-primary" href="%s" target="_blank">ورود به حساب کاربری</a>
                    </div>
                  </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
              </body>
            </html>  
            """;


    public static String getConfirmation(String code, String link) {
        return confirmation.formatted(code, link);
    }

    public static String getTemplate(String title, String text, String href, String hrefText) {
        return template.formatted(title, text, href, hrefText);
    }

    public static String getResetPasswordTemplate(String href) {
        return getTemplate("تغییر رمز عبور",
                """
                        از طریق دکمه زیر اقدام به تغییر رمز عبور کنید. اگر شما درخواستی جهت تغییر رمز عبور ندادید این ایمیل را نادیده بگیرید.
                        """,
                href,
                "تغییر رمز عبور");
    }
}
