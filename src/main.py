"""Mã mẫu tối thiểu để Semgrep / SonarCloud có nguồn phân tích trong demo."""


def greet(name: str) -> str:
    return f"Hello, {name}"


if __name__ == "__main__":
    print(greet("demo"))
