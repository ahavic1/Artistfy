package ba.ahavic.artistfy.ui.base

data class AppError(val reasonOfError: ReasonOfError)

enum class ReasonOfError {
    UnKnownHost,
    ServerError,
    InvalidAPIKey,
    InvalidRequestMethod,
    GenericError,
    DatabaseSaveFailed,
    InvalidParameters,
    MissingStoragePermissionsError
}

data class AppException(val appError: AppError): Exception()