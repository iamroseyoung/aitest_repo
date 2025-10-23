class TextSummarizer {
    constructor() {
        this.initializeElements();
        this.attachEventListeners();
    }

    initializeElements() {
        this.textInput = document.getElementById('textInput');
        this.summarizeBtn = document.getElementById('summarizeBtn');
        this.clearBtn = document.getElementById('clearBtn');
        this.resultSection = document.getElementById('resultSection');
        this.errorSection = document.getElementById('errorSection');
        this.resultContent = document.getElementById('resultContent');
        this.errorContent = document.getElementById('errorContent');
        this.copyBtn = document.getElementById('copyBtn');
        this.newSummaryBtn = document.getElementById('newSummaryBtn');
        this.retryBtn = document.getElementById('retryBtn');
        this.btnText = this.summarizeBtn.querySelector('.btn-text');
        this.spinner = this.summarizeBtn.querySelector('.spinner');
    }

    attachEventListeners() {
        this.summarizeBtn.addEventListener('click', () => this.handleSummarize());
        this.clearBtn.addEventListener('click', () => this.handleClear());
        this.copyBtn.addEventListener('click', () => this.handleCopy());
        this.newSummaryBtn.addEventListener('click', () => this.handleNewSummary());
        this.retryBtn.addEventListener('click', () => this.handleRetry());
        
        // Enter 키로 요약하기 (Ctrl+Enter)
        this.textInput.addEventListener('keydown', (e) => {
            if (e.ctrlKey && e.key === 'Enter') {
                this.handleSummarize();
            }
        });
    }

    async handleSummarize() {
        const text = this.textInput.value.trim();
        
        if (!text) {
            this.showError('텍스트를 입력해주세요.');
            return;
        }

        if (text.length < 50) {
            this.showError('텍스트가 너무 짧습니다. 최소 50자 이상 입력해주세요.');
            return;
        }

        this.setLoading(true);
        this.hideSections();

        try {
            const response = await fetch('/api/summarize', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ text: text })
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const result = await response.text();
            this.showResult(result);
            
        } catch (error) {
            console.error('Error:', error);
            this.showError(`요약 중 오류가 발생했습니다: ${error.message}`);
        } finally {
            this.setLoading(false);
        }
    }

    handleClear() {
        this.textInput.value = '';
        this.hideSections();
        this.textInput.focus();
    }

    handleCopy() {
        const resultText = this.resultContent.textContent;
        navigator.clipboard.writeText(resultText).then(() => {
            this.showToast('클립보드에 복사되었습니다!');
        }).catch(err => {
            console.error('복사 실패:', err);
            this.showToast('복사에 실패했습니다.');
        });
    }

    handleNewSummary() {
        this.hideSections();
        this.textInput.focus();
    }

    handleRetry() {
        this.hideSections();
        this.handleSummarize();
    }

    setLoading(isLoading) {
        this.summarizeBtn.disabled = isLoading;
        this.btnText.style.display = isLoading ? 'none' : 'inline';
        this.spinner.style.display = isLoading ? 'inline' : 'none';
    }

    showResult(result) {
        this.resultContent.textContent = result;
        this.resultSection.style.display = 'block';
        this.resultSection.classList.add('fade-in');
    }

    showError(message) {
        this.errorContent.textContent = message;
        this.errorSection.style.display = 'block';
        this.errorSection.classList.add('fade-in');
    }

    hideSections() {
        this.resultSection.style.display = 'none';
        this.errorSection.style.display = 'none';
        this.resultSection.classList.remove('fade-in');
        this.errorSection.classList.remove('fade-in');
    }

    showToast(message) {
        // 간단한 토스트 메시지 구현
        const toast = document.createElement('div');
        toast.textContent = message;
        toast.style.cssText = `
            position: fixed;
            top: 20px;
            right: 20px;
            background: #28a745;
            color: white;
            padding: 12px 20px;
            border-radius: 5px;
            z-index: 1000;
            animation: slideIn 0.3s ease;
        `;
        
        document.body.appendChild(toast);
        
        setTimeout(() => {
            toast.style.animation = 'slideOut 0.3s ease';
            setTimeout(() => document.body.removeChild(toast), 300);
        }, 2000);
    }
}

// CSS 애니메이션 추가
const style = document.createElement('style');
style.textContent = `
    @keyframes slideIn {
        from { transform: translateX(100%); opacity: 0; }
        to { transform: translateX(0); opacity: 1; }
    }
    @keyframes slideOut {
        from { transform: translateX(0); opacity: 1; }
        to { transform: translateX(100%); opacity: 0; }
    }
`;
document.head.appendChild(style);

// 앱 초기화
document.addEventListener('DOMContentLoaded', () => {
    new TextSummarizer();
});
