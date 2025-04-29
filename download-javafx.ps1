# PowerShell script to download JavaFX SDK
$javafxVersion = "17.0.2"
$os = "win"
$arch = "x64"
$downloadUrl = "https://download2.gluonhq.com/openjfx/$javafxVersion/openjfx-${javafxVersion}_${os}-${arch}_bin.zip"
$outputFile = "javafx-sdk.zip"
$extractPath = "lib"

Write-Host "Downloading JavaFX SDK $javafxVersion..."
Invoke-WebRequest -Uri $downloadUrl -OutFile $outputFile

Write-Host "Extracting JavaFX SDK..."
Expand-Archive -Path $outputFile -DestinationPath $extractPath -Force

Write-Host "Cleaning up..."
Remove-Item $outputFile

Write-Host "JavaFX SDK downloaded and extracted to $extractPath" 