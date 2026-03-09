# 🚀 GUÍA PASO A PASO - DEPLOYMENT CON RENDER

## ═══════════════════════════════════════════════════════════════
## PARTE 1: PREPARACIÓN (5 minutos)
## ═══════════════════════════════════════════════════════════════

### ✅ Pre-requisitos
- [ ] Cuenta de GitHub
- [ ] Repositorio HavenClinic-Backend en GitHub
- [ ] Repositorio HavenClinic-Angular-Frontend en GitHub
- [ ] Git instalado localmente

### 📝 Paso 1.1: Verificar que todo está commiteado

Abre PowerShell en la carpeta del backend:

```powershell
cd C:\Users\jonat\OneDrive\Documentos\GitHub\HavenClinic-Backend

# Ver estado de Git
git status

# Si hay cambios sin commitear:
git add .
git commit -m "Prepare backend for Render deployment"
git push origin main
```

**NOTA:** Si tu rama principal se llama `master` en vez de `main`, usa `git push origin master`

---

## ═══════════════════════════════════════════════════════════════
## PARTE 2: CREAR CUENTA Y CONFIGURAR RENDER (10 minutos)
## ═══════════════════════════════════════════════════════════════

### 📝 Paso 2.1: Crear cuenta en Render

1. **Abre tu navegador** y ve a: https://render.com

2. **Click en "Get Started"** (botón azul en la esquina superior derecha)

3. **Opciones de registro:**
   - Opción A (Recomendada): Click en **"Sign up with GitHub"**
   - Opción B: Usa tu email

4. **Si elegiste GitHub:**
   - Autoriza a Render acceder a tu cuenta de GitHub
   - Acepta los permisos solicitados

5. **Verifica tu email** si es necesario

6. **Ya estás en el Dashboard de Render** 🎉

---

### 📝 Paso 2.2: Conectar tu repositorio de GitHub

Si NO elegiste "Sign up with GitHub", necesitas conectar GitHub:

1. En el Dashboard de Render, click en tu foto de perfil (esquina superior derecha)
2. Click en **"Account Settings"**
3. En el menú izquierdo, click en **"GitHub"**
4. Click en **"Connect GitHub Account"**
5. Autoriza a Render

---

## ═══════════════════════════════════════════════════════════════
## PARTE 3: CREAR EL WEB SERVICE (5 minutos)
## ═══════════════════════════════════════════════════════════════

### 📝 Paso 3.1: Iniciar creación del servicio

1. **En el Dashboard**, click en el botón **"New +"** (esquina superior derecha)

2. **Selecciona "Web Service"** de las opciones

3. **Verás dos opciones:**
   - "Build and deploy from a Git repository" ← SELECCIONA ESTA
   - Click en **"Next"**

---

### 📝 Paso 3.2: Seleccionar tu repositorio

1. **Busca tu repositorio:**
   - En la barra de búsqueda, escribe: "HavenClinic-Backend"
   - O navega por la lista hasta encontrarlo

2. **Click en "Connect"** al lado de "HavenClinic-Backend"

3. **Si NO ves tu repositorio:**
   - Click en "Configure account" (abajo de la lista)
   - En GitHub, autoriza a Render acceder al repositorio específico
   - Regresa a Render y refresca la página

---

### 📝 Paso 3.3: Configurar el servicio

Render te mostrará un formulario. Complétalo así:

#### **Campos a llenar:**

**1. Name** (Nombre del servicio):
```
havenclinic-backend
```
(Este nombre aparecerá en tu URL)

**2. Region** (Región):
```
Oregon (US West) o Frankfurt (Europe)
```
(Elige la más cercana a tu ubicación)

**3. Branch** (Rama):
```
main
```
(O `master` si esa es tu rama principal)

**4. Root Directory** (Directorio raíz):
```
(dejar vacío)
```

**5. Runtime** (Sistema):
Render debería detectar automáticamente: **Java**
Si no, selecciona "Java" del dropdown

**6. Build Command** (Comando de construcción):
```
./mvnw clean install -DskipTests
```

**7. Start Command** (Comando de inicio):
```
java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar target/clinicahaven-0.0.1-SNAPSHOT.jar
```

---

### 📝 Paso 3.4: Seleccionar el plan

**8. Instance Type:**
Baja hasta encontrar la sección de planes:

```
[ ] Starter - $7/month
[X] Free - $0/month  ← SELECCIONA ESTE
```

**Características del plan Free:**
- 750 horas/mes (suficiente para 24/7)
- 512 MB RAM
- Servicio se "duerme" después de 15 min sin actividad
- Tarda 30-60 seg en "despertar"

---

### 📝 Paso 3.5: Variables de entorno (Environment Variables)

Baja hasta la sección **"Environment Variables"**

Click en **"Add Environment Variable"** y agrega:

**Variable 1:**
- Key: `SPRING_PROFILES_ACTIVE`
- Value: `prod`

**Variable 2:**
- Key: `JAVA_TOOL_OPTIONS`
- Value: `-Xmx450m -Xms256m`

(Esto optimiza el uso de memoria para el plan free)

---

### 📝 Paso 3.6: Opciones avanzadas (Optional pero recomendado)

Expande **"Advanced"** al final del formulario:

**Auto-Deploy:**
- [X] Yes (Recomendado)
  
Esto hará que Render redespliegue automáticamente cuando hagas push a GitHub.

---

### 📝 Paso 3.7: Crear el servicio

1. **Revisa toda la configuración**

2. **Click en el botón azul "Create Web Service"** (al final)

3. **¡Proceso iniciado!** 🚀

---

## ═══════════════════════════════════════════════════════════════
## PARTE 4: PROCESO DE DEPLOYMENT (5-15 minutos)
## ═══════════════════════════════════════════════════════════════

### 📝 Paso 4.1: Observar el despliegue

Verás una pantalla con logs en tiempo real:

```
==> Cloning from https://github.com/johny2004/HavenClinic-Backend...
==> Downloading JDK...
==> Running 'build.sh'
==> Building...
    Downloading from central: ...
    Downloaded: ...
    [INFO] BUILD SUCCESS
==> Uploading build...
==> Starting service...
    __  ____  __  _____   ___  __ ____  ______ 
       / / / / / / / __ \/ _ \/ //_/ / / / __/
      / /_/ / /_/ / /_/ /  __/ ,< / /_/ /\ \  
      \____/\____/ .___/\___/_/|_|\____/___/  
                /_/                           
    :: Spring Boot ::               (v3.3.2)
    
==> Your service is live! 🎉
```

**Tiempos aproximados:**
- Clonación: 30 segundos
- Build: 3-8 minutos
- Start: 30 segundos

---

### 📝 Paso 4.2: Obtener tu URL

1. **Una vez que veas "Your service is live!"**

2. **En la parte superior** verás tu URL:
   ```
   https://havenclinic-backend-xxxx.onrender.com
   ```
   
   (xxxx será un código único)

3. **COPIA esta URL completa** ✍️

4. **Pruébala:**
   - Abre una nueva pestaña
   - Pega la URL y agrega `/swagger-ui.html`
   - Ejemplo: `https://havenclinic-backend-xxxx.onrender.com/swagger-ui.html`
   - Deberías ver la documentación de tu API

---

### 📝 Paso 4.3: Solución de problemas durante el deploy

#### ❌ Si falla el build:

**Problema 1: "Maven Wrapper not found"**
```
Solución:
- En tu proyecto local, ejecuta: ./mvnw --version
- Si falla, ejecuta: mvn wrapper:wrapper
- Commit y push de nuevo
```

**Problema 2: "OutOfMemoryError"**
```
Solución:
- En Environment Variables, verifica JAVA_TOOL_OPTIONS
- Cambia a: -Xmx400m -Xms200m
- Click en "Manual Deploy" → "Clear build cache & deploy"
```

**Problema 3: "Build timeout"**
```
Solución:
- Render free tier tiene límite de 15 min
- En Build Command, agrega: -DskipTests -Dmaven.javadoc.skip=true
```

---

## ═══════════════════════════════════════════════════════════════
## PARTE 5: ACTUALIZAR EL FRONTEND (5 minutos)
## ═══════════════════════════════════════════════════════════════

### 📝 Paso 5.1: Actualizar environment.prod.ts

1. **Abre tu proyecto del frontend**

2. **Edita el archivo:**
   ```
   HavenClinic-Angular-Frontend/src/environments/environment.prod.ts
   ```

3. **Reemplaza la URL** con la que copiaste de Render:
   ```typescript
   export const environment = {
     production: true,
     apiUrl: 'https://havenclinic-backend-xxxx.onrender.com'  // ← TU URL AQUÍ
   };
   ```

   **⚠️ IMPORTANTE:** 
   - NO incluyas `/` al final
   - NO incluyas `/swagger-ui.html`
   - Solo la URL base

4. **Guarda el archivo**

---

### 📝 Paso 5.2: Commit y push del frontend

```powershell
cd C:\Users\jonat\OneDrive\Documentos\GitHub\HavenClinic-Angular-Frontend

git add .
git commit -m "Update production API URL"
git push origin main
```

---

## ═══════════════════════════════════════════════════════════════
## PARTE 6: DESPLEGAR EL FRONTEND A GITHUB PAGES (10 minutos)
## ═══════════════════════════════════════════════════════════════

### 📝 Paso 6.1: Instalar dependencias

```powershell
cd C:\Users\jonat\OneDrive\Documentos\GitHub\HavenClinic-Angular-Frontend

# Si no has instalado las dependencias:
npm install

# Verificar que gh-pages esté instalado:
npm list gh-pages
```

Si `gh-pages` no está instalado:
```powershell
npm install gh-pages --save-dev
```

---

### 📝 Paso 6.2: Build y deploy

```powershell
# Construir para producción y desplegar
npm run build:deploy
```

**Verás algo como:**
```
> ng build --configuration production --base-href /HavenClinic-Angular-Frontend/

✔ Browser application bundle generation complete.
✔ Copying assets complete.
✔ Index html generation complete.

Initial Chunk Files | Names     |  Raw Size
main-XXXXXX.js      | main      | 1.2 MB
polyfills-XXXX.js   | polyfills | 90 kB
styles-XXXXX.css    | styles    | 45 kB

Build at: 2026-03-09T...

> gh-pages -d dist/haven-angular

Published
```

**Tiempo estimado:** 2-5 minutos

---

### 📝 Paso 6.3: Habilitar GitHub Pages

1. **Ve a GitHub** en tu navegador

2. **Abre tu repositorio:** `HavenClinic-Angular-Frontend`

3. **Click en "Settings"** (pestaña superior)

4. **En el menú izquierdo, click en "Pages"**

5. **Configuración:**
   - **Source:** Selecciona `gh-pages` (rama)
   - **Folder:** Selecciona `/ (root)`
   - Click en **"Save"**

6. **Espera 1-2 minutos**

7. **Verás un mensaje verde:**
   ```
   Your site is live at https://johny2004.github.io/HavenClinic-Angular-Frontend/
   ```

8. **Click en "Visit site"** para probarlo

---

## ═══════════════════════════════════════════════════════════════
## PARTE 7: PRUEBAS Y VERIFICACIÓN (5 minutos)
## ═══════════════════════════════════════════════════════════════

### 📝 Paso 7.1: Probar el backend

1. **URL Swagger:**
   ```
   https://havenclinic-backend-xxxx.onrender.com/swagger-ui.html
   ```
   ✅ Deberías ver la documentación de la API

2. **Probar un endpoint:**
   - En Swagger, abre el endpoint `POST /login`
   - Click en "Try it out"
   - Ingresa las credenciales de prueba:
     ```json
     {
       "email": "a@c.m",
       "psw": "abc",
       "userType": "CLIENTE"
     }
     ```
   - Click en "Execute"
   - ✅ Deberías recibir un token JWT

---

### 📝 Paso 7.2: Probar el frontend

1. **Abre tu sitio:**
   ```
   https://johny2004.github.io/HavenClinic-Angular-Frontend/
   ```

2. **Intenta hacer login:**
   - Usuario: `a@c.m`
   - Contraseña: `abc`

3. **Abre DevTools (F12):**
   - Pestaña "Console"
   - Busca errores de CORS
   - Pestaña "Network"
   - Verifica que las peticiones vayan a tu backend de Render

4. **Si login funciona:** ✅ ¡TODO ESTÁ FUNCIONANDO!

---

### 📝 Paso 7.3: Verificar CORS

Si ves error de CORS en la consola:

```javascript
Access to XMLHttpRequest at 'https://havenclinic-backend-xxxx.onrender.com/...' 
from origin 'https://johny2004.github.io' has been blocked by CORS policy
```

**Solución:**
1. Ve a tu backend en GitHub
2. Verifica que `CorsConfig.java` tenga:
   ```java
   config.addAllowedOrigin("https://johny2004.github.io");
   ```
3. Si está correcto, Render redesployará automáticamente
4. Si cambiaste algo, espera 5 min y prueba de nuevo

---

## ═══════════════════════════════════════════════════════════════
## PARTE 8: GESTIÓN POST-DEPLOYMENT
## ═══════════════════════════════════════════════════════════════

### 📝 Monitoreo en Render

**Dashboard del servicio:**
https://dashboard.render.com/web/[tu-servicio-id]

**Cosas que puedes hacer:**
- Ver logs en tiempo real
- Reiniciar el servicio
- Ver métricas de uso
- Configurar notificaciones

**Pestaña "Logs":**
- Ver todos los logs de tu aplicación
- Buscar errores
- Ver peticiones HTTP

**Pestaña "Metrics":**
- Uso de RAM
- CPU
- Tráfico de red

---

### 📝 Comportamiento del plan Free

**Auto-Sleep:**
- Se "duerme" después de 15 minutos sin actividad
- Primera petición después del sleep: 30-60 segundos
- Peticiones subsecuentes: normales

**Cómo mantenerlo activo (opcional):**
1. Usa un servicio de ping gratis:
   - https://uptimerobot.com (gratis)
   - https://cron-job.org (gratis)
2. Configura ping cada 14 minutos a tu URL

---

### 📝 Actualizaciones futuras

**Backend:**
```powershell
# Hacer cambios en tu código
git add .
git commit -m "Descripción de cambios"
git push origin main
# Render redesploya automáticamente (5-10 min)
```

**Frontend:**
```powershell
# Hacer cambios en tu código
npm run build:deploy
# Actualiza en GitHub Pages (1-2 min)
```

---

## ═══════════════════════════════════════════════════════════════
## RESUMEN DE URLs IMPORTANTES
## ═══════════════════════════════════════════════════════════════

✅ **Backend API:**
`https://havenclinic-backend-xxxx.onrender.com`

✅ **Backend Swagger:**
`https://havenclinic-backend-xxxx.onrender.com/swagger-ui.html`

✅ **Frontend:**
`https://johny2004.github.io/HavenClinic-Angular-Frontend/`

✅ **Render Dashboard:**
`https://dashboard.render.com`

---

## ═══════════════════════════════════════════════════════════════
## TROUBLESHOOTING COMÚN
## ═══════════════════════════════════════════════════════════════

### ❌ Problema: "Application failed to start"

**Causas posibles:**
1. Puerto mal configurado
2. Perfil de Spring no activado
3. Dependencias faltantes

**Solución:**
```
1. En Render → Environment Variables, verifica:
   SPRING_PROFILES_ACTIVE=prod
   
2. En Logs, busca el error exacto

3. Si es de memoria: Ajusta JAVA_TOOL_OPTIONS
```

---

### ❌ Problema: "Build failed"

**Solución:**
```
1. En Render, click en "Manual Deploy"
2. Selecciona "Clear build cache & deploy"
3. Si persiste, verifica que mvnw tenga permisos de ejecución
```

---

### ❌ Problema: Frontend no conecta con backend

**Checklist:**
```
[ ] environment.prod.ts tiene la URL correcta
[ ] URL NO tiene slash (/) al final
[ ] CORS está configurado en CorsConfig.java
[ ] Backend está corriendo (prueba Swagger)
[ ] No hay errores en Console (F12)
```

---

### ❌ Problema: "502 Bad Gateway" o "Service Unavailable"

**Causa:** Backend dormido (free tier)

**Solución:** 
```
Espera 30-60 segundos y recarga la página
```

---

## 🎉 ¡FELICIDADES!

Si llegaste hasta aquí y todo funcionó, tu aplicación está 100% desplegada en producción.

**Siguiente paso:** Comparte tus URLs en tu portfolio o CV:
- Frontend demo: https://johny2004.github.io/HavenClinic-Angular-Frontend/
- API docs: https://tu-backend.onrender.com/swagger-ui.html

---

## 📞 Soporte

Si algo no funciona:
1. Revisa los logs en Render Dashboard
2. Revisa la consola del navegador (F12)
3. Verifica las variables de entorno
4. Consulta: https://render.com/docs/java

---

**Última actualización:** 9 de marzo de 2026
**Versión:** 1.0
