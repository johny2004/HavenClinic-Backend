# 🚀 Guía de Deployment - HavenClinic

## 📋 Resumen
Esta guía te ayudará a desplegar:
- **Frontend** → GitHub Pages
- **Backend** → Render

---

## PARTE 1: Desplegar Backend en Render (15 minutos)

### 1️⃣ Crear cuenta en Render
1. Ve a [https://render.com](https://render.com)
2. Regístrate con tu cuenta de GitHub

### 2️⃣ Conectar tu repositorio
1. En Render, haz clic en **"New +"** → **"Web Service"**
2. Conecta tu cuenta de GitHub si aún no lo has hecho
3. Busca y selecciona el repositorio **"HavenClinic-Backend"**
4. Haz clic en **"Connect"**

### 3️⃣ Configurar el servicio

**Configuración básica:**
- **Name:** `havenclinic-backend` (o el nombre que prefieras)
- **Region:** Selecciona la más cercana (ej: Oregon, USA)
- **Branch:** `main` (o la rama principal de tu proyecto)
- **Root Directory:** (dejar vacío)
- **Environment:** `Docker`
- **Plan:** **Free**

**Variables de entorno:**
Agrega estas variables:
```
SPRING_PROFILES_ACTIVE = prod
JAVA_TOOL_OPTIONS = -Xmx450m -Xms256m
```

### 4️⃣ Desplegar
1. Haz clic en **"Create Web Service"**
2. Espera 5-10 minutos mientras Render construye y despliega tu aplicación
3. **¡IMPORTANTE!** Copia la URL que Render te proporciona (ej: `https://havenclinic-backend.onrender.com`)

### 5️⃣ Verificar que funciona
Abre en tu navegador: `https://TU-URL-DE-RENDER.onrender.com/h2`
- Si ves la consola H2, ¡el backend está funcionando! ✅

---

## PARTE 2: Configurar Frontend (5 minutos)

### 1️⃣ Actualizar la URL del backend

En el proyecto **Angular Frontend**, edita este archivo:
```
HavenClinic-Angular-Frontend/src/environments/environment.prod.ts
```

Cambia la URL por la que copiaste de Render:
```typescript
export const environment = {
  production: true,
  apiUrl: 'https://TU-URL-DE-RENDER.onrender.com'  // ← PEGA TU URL AQUÍ (SIN / al final)
};
```

### 2️⃣ Hacer commit de los cambios
```bash
cd HavenClinic-Angular-Frontend
git add .
git commit -m "Update backend URL for production"
git push origin main
```

---

## PARTE 3: Desplegar Frontend en GitHub Pages (5 minutos)

### Opción A: Deployment manual

1. Abre terminal en la carpeta del frontend:
```bash
cd HavenClinic-Angular-Frontend
```

2. Instala dependencias (si no lo has hecho):
```bash
npm install
```

3. Build y deploy:
```bash
npm run build:deploy
```

4. Espera a que termine. Tu sitio estará disponible en:
```
https://johny2004.github.io/HavenClinic-Angular-Frontend/
```

### Opción B: Deployment automático con GitHub Actions

1. En tu repositorio de GitHub del frontend, ve a **Settings** → **Pages**
2. En **"Source"**, selecciona la rama **"gh-pages"**
3. Guarda los cambios

Cada vez que hagas push a main, el sitio se actualizará automáticamente.

---

## 🎉 ¡Listo! Verificación Final

### ✅ Checklist de verificación:

**Backend (Render):**
- [ ] El servicio está "Live" en Render
- [ ] La URL de Render funciona (prueba: `https://TU-URL.onrender.com/h2`)
- [ ] No hay errores en los logs de Render

**Frontend (GitHub Pages):**
- [ ] El sitio carga en `https://johny2004.github.io/HavenClinic-Angular-Frontend/`
- [ ] Puedes ver la página de inicio
- [ ] **Importante:** La primera carga puede tardar 1-2 minutos (Render Free Tier)

**Prueba de integración:**
1. Abre tu frontend en GitHub Pages
2. Intenta hacer login con:
   - Usuario: `a@c.m`
   - Contraseña: `abc`
3. Si el login funciona, ¡todo está conectado correctamente! 🎉

---

## ⚠️ Problemas Comunes

### Problema: "Failed to fetch" o error de CORS
**Solución:**
1. Verifica que la URL en `environment.prod.ts` sea correcta (sin `/` al final)
2. Asegúrate de haber hecho push al repositorio del frontend
3. Limpia caché del navegador y recarga

### Problema: Backend tarda mucho en responder
**Causa:** Render Free Tier pone el servicio en "sleep" después de 15 minutos de inactividad.
**Solución:** La primera petición después de sleep toma 30-60 segundos. Las siguientes son rápidas.

### Problema: No se ven los cambios en GitHub Pages
**Solución:**
1. Verifica que ejecutaste `npm run build:deploy`
2. Espera 2-3 minutos para que GitHub Pages actualice
3. Limpia caché del navegador (Ctrl+Shift+R)

---

## 🔄 Actualizaciones Futuras

### Para actualizar el Backend:
```bash
cd HavenClinic-Backend
# Haz tus cambios
git add .
git commit -m "Tu mensaje"
git push origin main
```
Render detectará el cambio y redesplegará automáticamente.

### Para actualizar el Frontend:
```bash
cd HavenClinic-Angular-Frontend
# Haz tus cambios
git add .
git commit -m "Tu mensaje"
git push origin main
npm run build:deploy
```

---

## 📞 URLs Finales

Una vez desplegado, guarda estos enlaces:

- **Frontend:** https://johny2004.github.io/HavenClinic-Angular-Frontend/
- **Backend:** https://TU-URL-DE-RENDER.onrender.com
- **Panel Render:** https://dashboard.render.com

---

## 💡 Tips adicionales

1. **Render Free Tier:** El servicio se duerme tras 15 min de inactividad. Usa servicios como [UptimeRobot](https://uptimerobot.com) para mantenerlo activo.

2. **GitHub Pages:** Los cambios pueden tardar 2-3 minutos en verse reflejados.

3. **Base de datos:** Con H2 en modo file, los datos persisten entre despliegues.

4. **Logs:** En Render dashboard → tu servicio → "Logs" para ver errores.

---

¡Felicidades! Tu aplicación ya está en la nube 🎉
