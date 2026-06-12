# Load .env file and run backend
$envFile = Join-Path $PSScriptRoot ".env"

if (Test-Path $envFile) {
    Get-Content $envFile | ForEach-Object {
        if ($_ -match '^\s*([^#=]+)=(.*)$') {
            $name = $matches[1].Trim()
            $value = $matches[2].Trim()
            [System.Environment]::SetEnvironmentVariable($name, $value, "Process")
            Set-Variable -Name $name -Value $value -Scope Global -ErrorAction SilentlyContinue
        }
    }
    Write-Host "Da load .env thanh cong" -ForegroundColor Green
} else {
    Write-Host "Khong tim thay file .env" -ForegroundColor Red
    exit 1
}

# Chay ung dung
Write-Host "Khoi dong server..."
cd $PSScriptRoot
